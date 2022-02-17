package com.salesianos.dam.anuel.MiarmaNetwork.media.service;

import com.salesianos.dam.anuel.MiarmaNetwork.media.model.FileMetadata;
import com.salesianos.dam.anuel.MiarmaNetwork.media.repo.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileRepository fileRepository;

    public FileMetadata upload(MultipartFile file, String nick) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        log.info("storing file {}", filename);

        FileMetadata metadata = fileStorageService.store(file, nick);
        return fileRepository.save(metadata);
    }

}
