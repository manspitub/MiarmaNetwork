package com.salesianos.dam.anuel.MiarmaNetwork.users.repo;

import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByNick(String nick);

    Optional<User> findByUserId(UUID id);

    Optional<User> findById(UUID uuid);

    Optional<User> findByEmail(String email);

    User findByNick(String nick);

    boolean existsByNick(String nick);

}
