package com.salesianos.dam.anuel.MiarmaNetwork.service;

import com.salesianos.dam.anuel.MiarmaNetwork.dto.CreatePublicacionDto;
import com.salesianos.dam.anuel.MiarmaNetwork.model.publicacion.Publicacion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PublicacionService {

    Publicacion save(CreatePublicacionDto publicacionDto, MultipartFile file);

    List<Publicacion> findAll();

    Optional<Publicacion> findbyId(Long id);



}
