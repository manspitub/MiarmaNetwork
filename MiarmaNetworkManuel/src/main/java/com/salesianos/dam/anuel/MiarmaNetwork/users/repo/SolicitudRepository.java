package com.salesianos.dam.anuel.MiarmaNetwork.users.repo;

import com.salesianos.dam.anuel.MiarmaNetwork.users.model.Solicitud;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {


    @Query("SELECT n FROM Solicitud n WHERE n.sender = ?1 AND n.reciver = ?2")
    Solicitud findSolicitudByBothUsers(User sender, User receiver);

    @Query("SELECT n FROM Solicitud n WHERE n.receiver = ?1")
    List<Solicitud> findReceivedSolicitudByUser(User user);


}
