package com.practice.project.service.authentication;

import com.practice.project.dao.primary.entity.Role;
import com.practice.project.dao.primary.entity.User;
import com.practice.project.dao.primary.repository.RoleRepository;
import com.practice.project.dao.primary.repository.UserRepository;
import com.practice.project.dto.*;
import com.practice.project.exception.BadRequestException;
import com.practice.project.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public AuthenticationRes login(final AuthenticationReq request) {
        final Supplier<NotFoundException> orElseThrow = () -> new NotFoundException("user not found");
        final var user = this.userRepository.findByUsername(request.getUsername())
                .filter(value -> this.passwordEncoder.matches(request.getPassword(),
                        value.getPassword()))
                .orElseThrow(orElseThrow);

        final var tokenValue = jwtService.createToken(request,user);
        var userResponse = modelMapper.map(user, UserRes.class);
        return new AuthenticationRes(tokenValue,userResponse);
    }

    @Transactional
    public RegisterUserRes register(final RegisterUserReq request) {
        final var optionalUser = this.userRepository.findByUsername(request.getUsername());

        if (optionalUser.isPresent()) {
            throw new BadRequestException("user already exists");
        }

        final var newUser = newUser(request);
        final var userSave = this.userRepository.save(newUser);
        final var userRes = modelMapper.map(userSave, UserRes.class);
        return new RegisterUserRes(userRes);
    }



    private User newUser(RegisterUserReq request){
        final var now = LocalDateTime.now();

        var user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        user.setCreatedOn(now);
        user.setUpdatedOn(now);

        Set<Role> roles = new HashSet<>();
        request.getRoles().forEach(roleToAdd -> {
            final var existingRole = roleRepository.findByName(roleToAdd);
            if(existingRole != null)roles.add(existingRole);
        });

        user.setRoles(roles);
        return user;
    }
}
