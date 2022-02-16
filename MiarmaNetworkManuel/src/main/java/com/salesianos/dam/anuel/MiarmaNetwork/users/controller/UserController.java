package com.salesianos.dam.anuel.MiarmaNetwork.users.controller;

import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.CreateUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.GetUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

    @PostMapping("/auth/register")
    public ResponseEntity<GetUserDto> getProfileUser(@RequestBody CreateUserDto nuevoUser){



    }


}
