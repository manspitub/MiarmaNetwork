package com.salesianos.dam.anuel.MiarmaNetwork.security.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUserResponse {

    private String nick;
    private String email;
    private String avatar;
    private LocalDateTime fechaNacimiento;
    private String biografia;
    private String phone;
    private String token;

}
