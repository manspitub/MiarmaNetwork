package com.salesianos.dam.anuel.MiarmaNetwork.users.dto;

import com.salesianos.dam.anuel.MiarmaNetwork.post.model.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.Role;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder



public class CreateUserDto {


    @NotBlank
    private String nick;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
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



}
