package com.practice.project.service.authentication;

import com.practice.project.configuration.AuthenticationProperties;
import com.practice.project.dao.primary.entity.User;
import com.practice.project.dto.AuthenticationReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder encoder;
    private final JwtDecoder jwtDecoder;
    private final AuthenticationProperties authenticationProperties;

    public String createToken(AuthenticationReq request, User user) {
        final var nowInstant = Instant.now();

        final var jwsHeader = JwsHeader.with(MacAlgorithm.HS256)
                .build();

        final var roles = user.getRoles().stream()
                .map(role -> "ROLE_" + role.getName())
                .toList();

        final var claims = JwtClaimsSet.builder()
                .issuer(this.authenticationProperties.getJwtIssuer())
                .issuedAt(nowInstant)
                .expiresAt(nowInstant.plusSeconds(this.authenticationProperties.getJwtExpiresAt()))
                .subject(request.getUsername())
                .claim("roles",roles)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(jwsHeader, claims))
                .getTokenValue();
    }

    public String getUsernameFromToken(String token) {
        Jwt jwt = this.jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    public boolean isTokenExpired(String token) {
        Jwt jwt = this.jwtDecoder.decode(token);
        Instant expirationTime = jwt.getExpiresAt();

        assert expirationTime != null;
        return expirationTime.isBefore(Instant.now());
    }
}