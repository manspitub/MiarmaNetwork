package com.salesianos.dam.anuel.MiarmaNetwork.users.service;

import com.salesianos.dam.anuel.MiarmaNetwork.media.service.FileStorageService;
import com.salesianos.dam.anuel.MiarmaNetwork.security.payload.RegisterRequest;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.Solicitud;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.SolicitudRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.UserRepository;
import com.salesianos.dam.anuel.MiarmaNetwork.users.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;


@Component
@RequiredArgsConstructor
public class UserService extends BaseService<User, UUID, UserRepository>implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;
    private final FileStorageService storageService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final SolicitudRepository solicitudRepository;


    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        return this.userRepository.findFirstByNick(nick)
                .orElseThrow(() -> new UsernameNotFoundException(nick + " no encontrado"));
    }


    public Optional<com.salesianos.dam.anuel.MiarmaNetwork.users.model.User> getUser(UUID id) {
        return userRepository.findById(id);
    }

    public User save(RegisterRequest userDto, MultipartFile file) {

        //TODO añadir excepciones

        if (userRepository.existsByNick(userDto.getNick())) {

            //Excepcion
        }
        String filename = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(filename)
                .toUriString();

        User usuario = User.builder()
                .nick(userDto.getNick())
                .avatar(uri)
                .email(userDto.getEmail())
                .fechaNacimiento(userDto.getFechaNacimiento())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        usuario.setPublic(userDto.isPublic());

        return userRepository.save(usuario);
    }

    public void requestFollow(User sender, User receiver){
        Solicitud solicitud = getSolicitud(sender, receiver);
        if (!receiver.getNick().equals(sender.getNick()) && !receiver.getFollowing().contains(sender)
        && solicitud == null) {
            solicitudRepository.save(new Solicitud(sender, receiver));
            return;
        }


    }

    public Solicitud getSolicitud(User sender, User receiver){
        return solicitudRepository.findSolicitudByBothUsers(sender, receiver);
    }

    public List<Solicitud> getReceivedSolicitudByUser(User user){
        return solicitudRepository.findReceivedSolicitudByUser(user);
    }
    public void follow(User sender, User receivers, UUID id, @AuthenticationPrincipal User currentUser){



        try {
            Optional<User> target = findById(id);
            if (target.isPresent()){
                currentUser.getFollowing().add(target.get());
                save(currentUser);
            }

        }catch (Exception e){
            log.info(e.getMessage());
        }
    }





    }














