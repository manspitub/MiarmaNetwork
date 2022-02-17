package com.salesianos.dam.anuel.MiarmaNetwork.users.dto;

import com.salesianos.dam.anuel.MiarmaNetwork.post.model.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class GetUserDto {

    private UUID id;
    private String nick;
    private String email;
    public String biografia;
    private String name;
    private String avatar;
    private LocalDateTime fechaNacimiento;
    private boolean isPublic;



}
