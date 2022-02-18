package com.salesianos.dam.anuel.MiarmaNetwork.post.repo;

import com.salesianos.dam.anuel.MiarmaNetwork.post.dto.GetPublicacionDto;
import com.salesianos.dam.anuel.MiarmaNetwork.post.model.Publicacion;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    Optional<User> findById(UUID id);

    public List<GetPublicacionDto> findByIsPublicTrue();
    public List<GetPublicacionDto> findByNick(String nick);
}
