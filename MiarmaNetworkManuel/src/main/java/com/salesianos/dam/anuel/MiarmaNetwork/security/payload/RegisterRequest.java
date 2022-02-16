package com.salesianos.dam.anuel.MiarmaNetwork.security.payload;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class RegisterRequest {



    @NotBlank
    @Size(min = 3, max = 15)
    private String nick;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotBlank
    private LocalDateTime fechaNacimiento;

    @NotBlank
    private boolean isPublic;

}
