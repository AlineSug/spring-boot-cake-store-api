package com.cakes.store.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cakes.store.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String createToken(User user){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            LocalDateTime expiresDate = LocalDateTime.now().plusHours(1);
            return JWT.create()
                    .withIssuer("Cake Store")
                    .withSubject(user.getLogin())
                    .withJWTId(UUID.randomUUID().toString())
                    .withExpiresAt(expiresDate.toInstant(ZoneOffset.of("-03:00")))
                    .sign(algoritimo);

        } catch (JWTCreationException e){

            throw new RuntimeException("Error in creation of the token ", e);

        }
    }

    public DecodedJWT verifyToken(String token){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("Cake Store")
                    .build()
                    .verify(token);

        } catch (JWTVerificationException ex){
            throw  new RuntimeException("Token is incorrectly");
        }
    }


    public String getUserToken(String token){
        return verifyToken(token).getSubject();
    }
}
