package com.salesianos.dam.anuel.MiarmaNetwork.users.dto;

import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestPart;

@Component
@RequiredArgsConstructor
public class UserDtoConverter {

    public GetUserDto userToGetUserDto(User u){
        return GetUserDto.builder()
                        .id(u.getId())
                                .nick(u.getNick())
                                        .email(u.getEmail())
                                                .avatar(u.getAvatar())
                                                        .isPublic(u.isPublic())
                                                                .name(u.getUsername())
                                                                        .phone(u.getPhone())
                                                                                .seguidores(u.getSeguidores())
                                                                                        .seguidos(u.getSeguidos())
                                                                                                .followsReceived(u.getFollowReceived())
                                                                                                        .followsRequest(u.getFollowsRequest())
                                                                                                                .fechaNacimiento(u.getFechaNacimiento())
                                                                                                                        .publicacionList(u.getPublicacionList())
                                                                                                                                .biografia(u.getBiografia())
                .build();
    }



}
