package com.salesianos.dam.anuel.MiarmaNetwork.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Publicacion {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    private String texto;


    private String file;



}
