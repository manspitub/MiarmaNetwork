package com.salesianos.dam.anuel.MiarmaNetwork.media.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Struct;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FileResponse {

    private String name;
    private String uri;
    private String type;
    private long size;
}
