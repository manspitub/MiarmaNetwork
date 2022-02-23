package com.salesianos.dam.anuel.MiarmaNetwork.post.service.impl;

import com.salesianos.dam.anuel.MiarmaNetwork.media.service.FileStorageService;
import com.salesianos.dam.anuel.MiarmaNetwork.post.dto.CreatePublicacionDto;
import com.salesianos.dam.anuel.MiarmaNetwork.post.model.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.post.repo.PublicacionRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.post.service.PublicacionService;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import com.salesianos.dam.anuel.MiarmaNetwork.users.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicacionServiceImpl extends BaseService<Publicacion, Long, PublicacionRepository> implements PublicacionService{

    private final PublicacionRepository repository;
    private final FileStorageService storageService;

    @Override
    public Publicacion save(CreatePublicacionDto publicacionDto, MultipartFile file, @AuthenticationPrincipal User currentUser) {
        String filename = storageService.storeNormal(file);


        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("download/")
                .path(filename)
                .toUriString();

        return repository.save(Publicacion.builder()
                .titulo(publicacionDto.getTitulo())
                .texto(publicacionDto.getTexto())
                .createAt(Instant.now())
                .file(uri)
                .user(currentUser)
                .isPublic(publicacionDto.isPublic())
                .build());
    }

    @Override
    public List<Publicacion> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Publicacion> findbyId(Long id) {
        return repository.findById(id);
    }


}
