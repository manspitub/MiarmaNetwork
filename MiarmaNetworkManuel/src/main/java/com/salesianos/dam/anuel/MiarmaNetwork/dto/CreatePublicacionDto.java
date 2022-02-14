package com.salesianos.dam.anuel.MiarmaNetwork.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.stereotype.Component;

@Value
@Builder
public class CreatePublicacionDto {

    private String titulo;
    private String texto;
    private String file;

}
