package com.salesianos.dam.anuel.MiarmaNetwork.model.publicacion;

import jdk.jfr.BooleanFlag;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PublicacionRequest {

    @NotBlank
    private String titulo;
    @NotBlank
    private String file;
    @NotBlank
    private String descripcion;

    private boolean isPublic;

}
