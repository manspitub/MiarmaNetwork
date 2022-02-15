package com.salesianos.dam.anuel.MiarmaNetwork.users.dto;

import com.salesianos.dam.anuel.MiarmaNetwork.model.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.Solicitud;
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
    private String name;
    private String avatar;
    private boolean isPublic;
    private LocalDateTime fechaNacimiento;
    private String phone ;
    private Set<User> seguidores;
    private Set<User> seguidos;
    private List<Publicacion> publicacionList;
    private Set<Solicitud> followsRequest;
    private Set<Solicitud> followsReceived;

}
