package com.cakes.store.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {

    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
