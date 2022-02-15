package com.salesianos.dam.anuel.MiarmaNetwork.security.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private String nick;

    @NotBlank
    private String password;
}