package com.salesianos.dam.anuel.MiarmaNetwork.security.payload;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class LoginRequest {

    @NotBlank
    private String nick;

    @NotBlank
    private String password;

    private String avatar;

    @DateTimeFormat()
    private LocalDateTime fechaNacimiento;
}