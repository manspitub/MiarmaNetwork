package com.salesianos.dam.anuel.MiarmaNetwork.users.service;

import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.GetUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;

import java.util.UUID;

public interface SolicitudService {

    void acceptSolicitud(User userFollower, User userFollowed);

    void declineSolicitud(User userFollower, User userFollowed);
}
