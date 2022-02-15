package com.salesianos.dam.anuel.MiarmaNetwork.controller;


import com.salesianos.dam.anuel.MiarmaNetwork.dto.FileResponse;
import com.salesianos.dam.anuel.MiarmaNetwork.service.StorageService;
import com.salesianos.dam.anuel.MiarmaNetwork.utils.MediaTypeUrlResource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final StorageService storageService;

    public ResponseEntity<?> upload (@RequestPart("file")MultipartFile file){
        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(name)
                .toUriString();

        FileResponse response = FileResponse.builder()
                .name(name)
                .size(file.getSize())
                .type(file.getContentType())
                .uri(uri)
                .build();

        return ResponseEntity.created(URI.create(uri)).body(response);
    }

    @GetMapping
    public ResponseEntity<Resource> getFile (@PathVariable String filename) {
        MediaTypeUrlResource resource = (MediaTypeUrlResource) storageService.loadAsResource(filename);

        return ResponseEntity.status(HttpStatus.OK)
                .header("content-type", resource.getType())
                .body(resource);
    }
}
