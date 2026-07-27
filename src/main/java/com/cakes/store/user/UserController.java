package com.cakes.store.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("role/{id}")
    public ResponseEntity<UserData> updateRole(@PathVariable Long id, @RequestBody @Valid RoleUpdateDTO dto){
        UserData updated = userService.updateRole(id, dto);
        return ResponseEntity.ok(updated);
    }
}
