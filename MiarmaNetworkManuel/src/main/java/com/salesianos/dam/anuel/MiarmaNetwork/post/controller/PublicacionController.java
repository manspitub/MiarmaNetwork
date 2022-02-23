package com.salesianos.dam.anuel.MiarmaNetwork.post.controller;

import com.salesianos.dam.anuel.MiarmaNetwork.media.service.FileStorageService;
import com.salesianos.dam.anuel.MiarmaNetwork.post.dto.CreatePublicacionDto;
import com.salesianos.dam.anuel.MiarmaNetwork.post.dto.GetPublicacionDto;
import com.salesianos.dam.anuel.MiarmaNetwork.post.dto.PublicacionDtoConverter;
import com.salesianos.dam.anuel.MiarmaNetwork.post.model.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.post.repo.PublicacionRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.post.service.PublicacionService;
import com.salesianos.dam.anuel.MiarmaNetwork.post.service.impl.PublicacionServiceImpl;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicacion")
@RequiredArgsConstructor
public class PublicacionController {

    private final PublicacionRepository repository;
    private final PublicacionService service;
    private final PublicacionServiceImpl serviceImpl;
    private final FileStorageService storageService;
    private final PublicacionDtoConverter converter;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestPart("file")MultipartFile file,
                                        @RequestPart("publicacion")CreatePublicacionDto newPost, @AuthenticationPrincipal User currentUser){

        Optional<User> userBuscar = repository.findById(currentUser.getId());

        if (userBuscar.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(newPost, file, userBuscar.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    public ResponseEntity<GetPublicacionDto> edit(@PathVariable Long id, @RequestPart ("file")MultipartFile file,
                                                  @RequestPart("publicacion")CreatePublicacionDto updatePost, @AuthenticationPrincipal User currentUser){


        String newFilename = storageService.storeNormal(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(newFilename)
                .toUriString();





        Optional<Publicacion> publicacionEdit = service.findbyId(id);
        if (publicacionEdit.isPresent()){

            publicacionEdit.get().setFile(null);

            publicacionEdit.map(p -> {
                p.setTitulo(publicacionEdit.get().getTitulo());
                p.setTexto(publicacionEdit.get().getTexto());
                p.setFile(uri);
                return p;
            });
            repository.save(publicacionEdit.get());

            return ResponseEntity.ok(converter.publicacionToGetPublicacionDto(publicacionEdit.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePublicacion(@PathVariable Long id){

        Optional<Publicacion> publiABorrar = service.findbyId(id);
        publiABorrar.ifPresent(publicacion -> publicacion.setFile(null));

            publiABorrar.ifPresent(serviceImpl::delete);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/public}")
    public ResponseEntity<List<GetPublicacionDto>> findAllPublic(){
        List<GetPublicacionDto> listPost = repository.findByIsPublicTrue();

        service.findAll().forEach(p->{
            listPost.add(converter.publicacionToGetPublicacionDto(p));
        });

        return ResponseEntity.ok(listPost);


    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPublicacionDto> seePost(@PathVariable Long id) {
        Optional<Publicacion> publi = service.findbyId(id);


        //Falta Comprobar por seguidor
        if (publi.isPresent()) {
            if (publi.get().isPublic()) {
                return ResponseEntity.ok(converter.publicacionToGetPublicacionDto(publi.get()));
            } else {
                ResponseEntity.status(HttpStatus.FORBIDDEN);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("{nick}")
    public ResponseEntity<List<GetPublicacionDto>> getPostByNick(@PathVariable String nick){

        List <GetPublicacionDto> listByNick = repository.findByNick(nick);

        service.findAll().forEach(p->{
            listByNick.add(converter.publicacionToGetPublicacionDto(p));
        });

        return ResponseEntity.ok(listByNick);


    }

    @GetMapping("/me")
    public ResponseEntity<List<GetPublicacionDto>> getPostMe(@AuthenticationPrincipal User user){
        List<GetPublicacionDto> listPost = repository.findByNick(user.getNick());

        if (listPost != null)

            service.findAll().forEach(publicacion -> {
                listPost.add(converter.publicacionToGetPublicacionDto(publicacion));
            });
        else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listPost);
    }





}
