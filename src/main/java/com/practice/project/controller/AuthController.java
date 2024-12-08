package com.practice.project.controller;

import com.practice.project.dto.*;
import com.practice.project.service.authentication.AuthenticationService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResultResponse<AuthenticationRes>> login(@Valid @RequestBody AuthenticationReq authenticationReq) {
        final var loginResponse = authenticationService.login(authenticationReq);
        return ResponseEntity.ok(ResultResponse.success(loginResponse));
    }

    @PostMapping("/register")
    public ResponseEntity<ResultResponse<RegisterUserRes>> register(@Valid @RequestBody RegisterUserReq registerUserReq) {
        final var registerUserResponse = authenticationService.register(registerUserReq);
        return ResponseEntity.ok(ResultResponse.success(registerUserResponse));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/info")
    public ResponseEntity<String> test(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok("successful");
    }

    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/test")
    public ResponseEntity<String> test2(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok("successful");
    }
}