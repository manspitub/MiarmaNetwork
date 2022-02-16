package com.salesianos.dam.anuel.MiarmaNetwork.users.dto;

import com.salesianos.dam.anuel.MiarmaNetwork.model.publicacion.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.Role;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.Solicitud;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder



public class CreateUserDto {



    private String nick;


    private String email;

    private String password;


    private String avatar;

    private boolean isPublic;

    private LocalDateTime fechaNacimiento;


    private String biografia;

    private String nombre;

    private Role roles;

    private String phone;

    private List<Publicacion> publicacionList;

    private Set<User> seguidores;

    private Set<User> seguidos;

    private Set<Solicitud> followsRequest;

    private Set<Solicitud> followReceived;

}
