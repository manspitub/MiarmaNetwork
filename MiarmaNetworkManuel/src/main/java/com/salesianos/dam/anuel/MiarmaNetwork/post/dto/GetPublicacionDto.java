package com.salesianos.dam.anuel.MiarmaNetwork.post.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetPublicacionDto {

    private String titulo;
    private String texto;
    private String file;


}
