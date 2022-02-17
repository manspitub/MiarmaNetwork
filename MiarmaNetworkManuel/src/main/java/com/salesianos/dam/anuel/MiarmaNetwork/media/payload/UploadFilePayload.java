package com.salesianos.dam.anuel.MiarmaNetwork.media.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFilePayload {

    private String filename;
    private String uri;
    private String fileType;

}
