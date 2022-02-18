package com.salesianos.dam.anuel.MiarmaNetwork.post.dto;

import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.Builder;
import lombok.Value;
import org.springframework.stereotype.Component;

@Value
@Builder
public class CreatePublicacionDto {

    private String titulo;
    private String texto;
    private String file;
    private boolean isPublic;
    private User user;


}
