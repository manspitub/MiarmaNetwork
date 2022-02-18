package com.salesianos.dam.anuel.MiarmaNetwork.post.dto;

import com.salesianos.dam.anuel.MiarmaNetwork.post.model.Publicacion;
import org.springframework.stereotype.Component;

@Component
public class PublicacionDtoConverter {

    public Publicacion createPublicacionDtoToPublicacion(CreatePublicacionDto p){

        return Publicacion.builder()
                .titulo(p.getTitulo())
                .texto(p.getTexto())
                .file(p.getFile())
                .isPublic(p.isPublic())
                .user(p.getUser())
                .build();
    }

    public GetPublicacionDto publicacionToGetPublicacionDto(Publicacion p){

        return GetPublicacionDto.builder()
                .titulo(p.getTitulo())
                .texto(p.getTexto())
                .file(p.getFile())
                .creator(p.getUser())
                .build();
    }

}
