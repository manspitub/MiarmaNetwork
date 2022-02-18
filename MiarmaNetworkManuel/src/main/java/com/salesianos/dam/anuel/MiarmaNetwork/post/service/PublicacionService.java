package com.salesianos.dam.anuel.MiarmaNetwork.post.service;

import com.salesianos.dam.anuel.MiarmaNetwork.post.dto.CreatePublicacionDto;
import com.salesianos.dam.anuel.MiarmaNetwork.post.model.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PublicacionService {


    Publicacion save(CreatePublicacionDto publicacionDto, MultipartFile file, @AuthenticationPrincipal User currentUser);

    List<Publicacion> findAll();



    Optional<Publicacion> findbyId(Long id);





}
