package com.salesianos.dam.anuel.MiarmaNetwork.users.controller;

import com.salesianos.dam.anuel.MiarmaNetwork.security.payload.RegisterRequest;
import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.CreateUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.GetUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.UserDtoConverter;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.Solicitud;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.SolicitudRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.UserRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService service;

    private final UserDtoConverter converter;

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final SolicitudRepository solicitudRepository;





    //Endpoint registro
    @PostMapping("/auth/register")
    public ResponseEntity<?> newUser(@RequestPart("file")MultipartFile file,
                                              @RequestPart("user") RegisterRequest newUser){

        User usuario = service.save(newUser, file);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(newUser, file));


    }



    @GetMapping("/me")
    public ResponseEntity<?> getMyUser(@AuthenticationPrincipal User currentUser){

        return ResponseEntity.ok(converter.userToGetUserDto(currentUser));

    }

    @PostMapping("follow/{nick}")
    public ResponseEntity<?> requestAFollow(@AuthenticationPrincipal User currentUser, @PathVariable String nick){

        Optional<User> userToFollow = repository.findFirstByNick(nick);

        if (userToFollow.isPresent()){
            service.requestFollow(currentUser, userToFollow.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }



    }
    @PostMapping("/follow/accept/{id}")
    public ResponseEntity<?> acceptRequestFollow(@AuthenticationPrincipal User currentUser, @PathVariable Long id){



        Optional <Solicitud> solicitud = solicitudRepository.findById(id);

        if (solicitud.isPresent()){
            if (solicitud.get().getReceived().equals(currentUser)){
                solicitudRepository.delete(solicitud.get());
                currentUser.getFollowing().add(solicitud.get().getSender());
                service.save(currentUser);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok().build();


    }

    @PostMapping("follow/decline/{id}")
    public ResponseEntity<?> declineRequestFollow(@AuthenticationPrincipal User currentUser, @PathVariable Long id){

        Optional<Solicitud> solicitud = solicitudRepository.findById(id);

        if (solicitud.isPresent()){
            if (solicitud.get().getReceived().equals(currentUser)){
                solicitudRepository.delete(solicitud.get());

            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/follow/list")
    public ResponseEntity<?> getSolicitudes(@AuthenticationPrincipal User currentUser){

        return ResponseEntity.ok(service.getReceivedSolicitudByUser(currentUser));

    }



}
