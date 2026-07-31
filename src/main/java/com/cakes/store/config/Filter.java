package com.cakes.store.config;

import com.cakes.store.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(Filter.class);
    private final TokenService tokenService;

    private final UserRepository userRepository;

    private final RevokedTokenRepository revokedTokenRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = getTheToken(request);
        if(token != null){
            try {
                var decoded = tokenService.verifyToken(token);

                boolean revoked = revokedTokenRepository.existsById(decoded.getId());
                if(!revoked){
                    var user = userRepository.findByLogin(decoded.getSubject());
                    log.warn("User={} authorities={}", decoded.getSubject(), user.getAuthorities());
                    var authenticator = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticator);
                }else {
                    log.warn("Token with jti={} has in backlist (Revoked)", decoded.getId());
                }
            } catch (RuntimeException ex){
                    log.warn("Error to Authenticate token:  {}", ex.getMessage(), ex);
            }
        }


        filterChain.doFilter(request, response);
    }

    private String getTheToken(HttpServletRequest request){
        var authorization = request.getHeader("Authorization");

        if(authorization != null) {
            return authorization.replace("Bearer ", "");
        }
        return null;
    }
}
