package com.salesianos.dam.anuel.MiarmaNetwork.service.impl;

import com.salesianos.dam.anuel.MiarmaNetwork.config.StorageProperties;
import com.salesianos.dam.anuel.MiarmaNetwork.exceptions.FileNotFoundException;
import com.salesianos.dam.anuel.MiarmaNetwork.exceptions.StorageException;
import com.salesianos.dam.anuel.MiarmaNetwork.service.StorageService;
import com.salesianos.dam.anuel.MiarmaNetwork.utils.MediaTypeUrlResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.MalformedParametersException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException exception){
            throw new StorageException("Could not initialize storage location", exception);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String newFilename = "";
        try {
            // Si el fichero está vacío, excepción al canto
            if (file.isEmpty())
                throw new StorageException("El fichero subido está vacío");

            newFilename = filename;
            while(Files.exists(rootLocation.resolve(newFilename))) {
                // Tratamos de generar uno nuevo
                String extension = StringUtils.getFilenameExtension(newFilename);
                String name = newFilename.replace("."+extension,"");

                String suffix = Long.toString(System.currentTimeMillis());
                suffix = suffix.substring(suffix.length()-6);

                newFilename = name + "_" + suffix + "." + extension;

            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, rootLocation.resolve(newFilename),
                        StandardCopyOption.REPLACE_EXISTING);
            }



        } catch (IOException ex) {
            throw new StorageException("Error en el almacenamiento del fichero: " + newFilename, ex);
        }

        return newFilename;

    }



    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);

        } catch (IOException exception) {
            throw new StorageException("Error al leer los ficheros almacenados");
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {

            Path file = load(filename);
            MediaTypeUrlResource resource = new MediaTypeUrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }
            else {
                throw new FileNotFoundException("Could not read file: "+filename);
            }

        } catch (MalformedURLException e){
            throw new FileNotFoundException("Could not read file" +filename, e);
        }
    }

    @Override
    public void deleteFile(String filename) {

    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
