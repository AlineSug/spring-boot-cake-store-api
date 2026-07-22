package com.cakes.store.config;

import com.cakes.store.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = getTheToken(request);
        if(token != null){
            var userLogin = tokenService.getUserToken(token);
            var user = userRepository.findByLogin(userLogin);

            var authenticator = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticator);
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
