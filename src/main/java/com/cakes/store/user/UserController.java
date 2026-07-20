package com.cakes.store.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserData> register(@RequestBody @Valid UserData dto, UriComponentsBuilder uriComponentsBuilder){

        UserData userDTO = userService.createUser(dto);
        URI adress = uriComponentsBuilder.path("/users/{id}")
                .buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(adress).body(userDTO);
    }
}
