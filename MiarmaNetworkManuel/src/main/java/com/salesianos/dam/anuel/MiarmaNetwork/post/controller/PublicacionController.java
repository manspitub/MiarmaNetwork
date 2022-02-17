package com.salesianos.dam.anuel.MiarmaNetwork.post.controller;

import com.salesianos.dam.anuel.MiarmaNetwork.post.dto.CreatePublicacionDto;
import com.salesianos.dam.anuel.MiarmaNetwork.post.dto.GetPublicacionDto;
import com.salesianos.dam.anuel.MiarmaNetwork.post.model.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.post.repo.PublicacionRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.post.service.PublicacionService;
import com.salesianos.dam.anuel.MiarmaNetwork.service.StorageService;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/publicacion")
@RequiredArgsConstructor
public class PublicacionController {

    private final PublicacionRepository repository;
    private final PublicacionService service;
    private final StorageService storageService;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestPart("file")MultipartFile file,
                                        @RequestPart("publicacion")CreatePublicacionDto newPost, @AuthenticationPrincipal User currentUser){

        Optional<User> userBucar = repository.findById(currentUser.getId());

        if (userBucar.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(newPost, file, userBucar.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    public ResponseEntity<GetPublicacionDto> edit(@PathVariable Long id, @RequestPart ("file")MultipartFile file,
                                                  @RequestPart("publicacion")CreatePublicacionDto updatePost, @AuthenticationPrincipal User currentUser){



        Optional<Publicacion> publicacionEdit = service.findbyId(id);
        if (publicacionEdit.isPresent()){
            publicacionEdit.map(p -> {
                p.setTitulo(publicacionEdit.get().getTitulo());
                p.setTexto(publicacionEdit.get().getTexto());
                p.getFile()
            })
        }
    }

}
