package com.salesianos.dam.anuel.MiarmaNetwork.controller;


import com.salesianos.dam.anuel.MiarmaNetwork.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final StorageService storageService;

    public ResponseEntity<?> upload (@RequestPart("file")MultipartFile file){
        return  ResponseEntity.ok().build();
    }
}
