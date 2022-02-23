package com.salesianos.dam.anuel.MiarmaNetwork.media.service;

import com.salesianos.dam.anuel.MiarmaNetwork.media.config.ResourceConfig;
import com.salesianos.dam.anuel.MiarmaNetwork.media.exceptions.FileNotFoundException;
import com.salesianos.dam.anuel.MiarmaNetwork.media.exceptions.StorageException;

import com.salesianos.dam.anuel.MiarmaNetwork.media.utils.MediaTypeUrlResource;
import io.github.techgnious.IVCompressor;
import io.github.techgnious.dto.IVSize;
import io.github.techgnious.dto.ImageFormats;
import io.github.techgnious.dto.VideoFormats;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.beans.BeanProperty;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private final Path rootLocation;

    @Autowired
    public FileStorageService(ResourceConfig properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }


    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }


    public String storeNormal(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String newFilename = "";
        try {
            if (file.isEmpty())
                throw new StorageException("El fichero subido está vacío");

            newFilename = filename;
            while(Files.exists(rootLocation.resolve(newFilename))) {
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

    public String storeResizedImage(MultipartFile file, int width)throws Exception{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        String extension = StringUtils.getFilenameExtension(filename);

        String name = filename.replace("."+extension,"");

        IVCompressor compressor = new IVCompressor();
        IVSize resolution = new IVSize();
        resolution.setWidth(width);
        resolution.setHeight(width);

        byte[] input = compressor.resizeImageWithCustomRes(file.getBytes(), ImageFormats.JPEG, resolution);

        ByteArrayInputStream bis = new ByteArrayInputStream(input);

        try {

            if (file.isEmpty())
                throw new StorageException("El fichero subido está vacío");


            while(Files.exists(rootLocation.resolve(filename))) {


                String suffix = Long.toString(System.currentTimeMillis());
                suffix = suffix.substring(suffix.length()-6);

                filename = name + "_" + suffix + "." + extension;
            }

            try (InputStream inputStream = bis) {
                Files.copy(inputStream, rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }



        } catch (IOException ex) {
            throw new StorageException("Error en el almacenamiento del fichero: " + filename, ex);
        }


        return filename;
    }

    public String storeVideoResized(MultipartFile file, int width) throws IOException, Exception {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        String extension = StringUtils.getFilenameExtension(filename);
        String name = filename.replace("."+extension,"");

        IVCompressor compressor = new IVCompressor();
        IVSize customRes = new IVSize();
        customRes.setWidth(width);
        customRes.setHeight(width);
        byte[] inputS = compressor.reduceVideoSizeWithCustomRes(file.getBytes(), VideoFormats.MP4, customRes);

        ByteArrayInputStream bis = new ByteArrayInputStream(inputS);

        while(Files.exists(rootLocation.resolve(filename))) {


            String suffix = Long.toString(System.currentTimeMillis());
            suffix = suffix.substring(suffix.length()-6);

            filename = name + "_" + suffix + "." + extension;
        }

        try {

            if (file.isEmpty())
                throw new StorageException("El fichero subido está vacío");


            try (InputStream inputStream = bis) {
                Files.copy(inputStream, rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }



        } catch (IOException ex) {
            throw new StorageException("Error en el almacenamiento del fichero: " + filename, ex);
        }


        return filename;
    }


    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Error al leer los ficheros almacenados", e);
        }
    }


    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }


    public Resource loadAsResource(String filename) {

        try {
            Path file = load(filename);
            MediaTypeUrlResource resource = new MediaTypeUrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new FileNotFoundException(
                        "Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
    }


    public void deleteFile(Path filePath) throws IOException {

        MediaTypeUrlResource mediaTypeUrlResource = new MediaTypeUrlResource(filePath.toUri());

        try {
            if (mediaTypeUrlResource.exists() || mediaTypeUrlResource.isReadable()) {
                Files.delete(filePath);
            } else {
                throw new FileNotFoundException(
                        "No se ha podido leer el archivo: " + filePath);
            }
        }catch (MalformedURLException e) {
            throw new FileNotFoundException("No se ha podido leer el archivo: " + filePath, e);
        }
    }



    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

}


