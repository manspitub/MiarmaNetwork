package com.salesianos.dam.anuel.MiarmaNetwork.users.controller;

import com.salesianos.dam.anuel.MiarmaNetwork.security.payload.RegisterRequest;
import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.CreateUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.GetUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.UserRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public class UserController {

    @Autowired
    public UserService service;

    @PostMapping("/auth/register")
    public ResponseEntity<?> newUser(@RequestPart("file")MultipartFile file,
                                              @RequestPart("user") RegisterRequest newUser){

        User usuario = service.save(newUser, file);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(newUser, file));


    }


}
