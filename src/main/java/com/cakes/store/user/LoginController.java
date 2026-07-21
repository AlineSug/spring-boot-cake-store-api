package com.cakes.store.user;

import com.cakes.store.config.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticator;
    private final TokenService tokenService;

@PostMapping
    public ResponseEntity userCredentialValidation(@RequestBody @Valid UserCredentials credentials){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getLogin(),credentials.getPassword());
        Authentication authentication = authenticator.authenticate(token);
        return ResponseEntity.ok(tokenService.createToken((User) authentication.getPrincipal()));
    }
}
