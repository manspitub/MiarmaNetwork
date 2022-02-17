package com.salesianos.dam.anuel.MiarmaNetwork.users.repo;

import com.salesianos.dam.anuel.MiarmaNetwork.users.model.Solicitud;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Solicitud s WHERE (s.sender = :user AND s.receiver = :followed)" +
    "OR (s.sender = :followed AND s.receiver = :user)")
    void deleteSolicitud(@Param("user")User user, @Param("followed")User followed);
}
