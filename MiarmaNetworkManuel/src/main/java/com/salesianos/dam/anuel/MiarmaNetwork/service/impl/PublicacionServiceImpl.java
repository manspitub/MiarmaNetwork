package com.salesianos.dam.anuel.MiarmaNetwork.service.impl;

import com.salesianos.dam.anuel.MiarmaNetwork.dto.CreatePublicacionDto;
import com.salesianos.dam.anuel.MiarmaNetwork.model.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.repo.PublicacionRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.service.PublicacionService;
import com.salesianos.dam.anuel.MiarmaNetwork.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicacionServiceImpl implements PublicacionService {

    private final StorageService storageService;
    private final PublicacionRepository publicacionRepository;

    @Override
    public Publicacion save(CreatePublicacionDto publicacionDto, MultipartFile file) {

        String fileName = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(fileName)
                .toUriString();
        return publicacionRepository.save(Publicacion.builder()
                .titulo(publicacionDto.getTitulo())
                .texto(publicacionDto.getTexto())
                .file(uri)
                .build());

    }

    @Override
    public List<Publicacion> findAll() {
        return publicacionRepository.findAll();
    }



    public Optional<Publicacion> findbyId(Long id) {
        return publicacionRepository.findById(id);
    }
}
