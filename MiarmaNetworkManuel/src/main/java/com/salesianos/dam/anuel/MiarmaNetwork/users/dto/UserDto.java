package com.salesianos.dam.anuel.MiarmaNetwork.users.dto;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;

    @NotNull
    private String nick;

    @NotNull
    @
    private String password

}
