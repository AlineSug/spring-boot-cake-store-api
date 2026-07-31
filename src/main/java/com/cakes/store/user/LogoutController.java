package com.cakes.store.user;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cakes.store.config.RevokedToken;
import com.cakes.store.config.RevokedTokenRepository;
import com.cakes.store.config.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequiredArgsConstructor
public class LogoutController {

    private final TokenService tokenService;
    private final RevokedTokenRepository revokedTokenRepository;

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        String token = extractToken(request);
        if(token != null){
            DecodedJWT decoded = tokenService.verifyToken(token);
            LocalDateTime expiration = LocalDateTime.ofInstant(
                    decoded.getExpiresAt().toInstant(), ZoneOffset.of("-03:00"));
            revokedTokenRepository.save(new RevokedToken(decoded.getId(), expiration));
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();

    }

    private String extractToken(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        if(authorization != null){
            return authorization.replace("Bearer ", "");
        }
        return null;
    }
}
