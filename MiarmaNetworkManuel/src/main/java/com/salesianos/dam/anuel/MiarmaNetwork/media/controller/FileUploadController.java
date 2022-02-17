package com.salesianos.dam.anuel.MiarmaNetwork.media.controller;

import com.salesianos.dam.anuel.MiarmaNetwork.media.model.FileMetadata;
import com.salesianos.dam.anuel.MiarmaNetwork.media.payload.UploadFilePayload;
import com.salesianos.dam.anuel.MiarmaNetwork.media.service.FileService;
import com.salesianos.dam.anuel.MiarmaNetwork.media.service.FileStorageService;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @PostMapping("/files")
    public UploadFilePayload uploadFile(@RequestParam("file")MultipartFile file,
                                        @AuthenticationPrincipal User user){
        String filename= StringUtils.cleanPath(file.getOriginalFilename());

        FileMetadata metadata = fileService.upload(file, user.getNick());

        return new UploadFilePayload(metadata.getName(), metadata.getUri(), metadata.getType());
    }
}
