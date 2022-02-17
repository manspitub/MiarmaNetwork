package com.salesianos.dam.anuel.MiarmaNetwork.users.service.impl;

import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.GetUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.SolicitudRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.UserRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.users.service.SolicitudService;
import com.salesianos.dam.anuel.MiarmaNetwork.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private UserService userService;
    private SolicitudRepository solicitudRepository;

    @Override
    @Transactional
    public void acceptSolicitud(User userFollower, User userFollowing) {
        solicitudRepository.deleteSolicitud(userFollower, userFollowing);
        userService.doFollow(userFollower, userFollower);





    }



    @Override
    @Transactional
    public void declineSolicitud(User userFollower, User userFollowing) {
        solicitudRepository.deleteSolicitud(userFollower, userFollowing);
    }
}
