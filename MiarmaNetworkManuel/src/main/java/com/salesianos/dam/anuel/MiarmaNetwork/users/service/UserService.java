package com.salesianos.dam.anuel.MiarmaNetwork.users.service;

import com.salesianos.dam.anuel.MiarmaNetwork.service.StorageService;
import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.CreateUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.dto.GetUserDto;
import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;
    private final StorageService storageService;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        return this.userRepository.findFirstByNick(nick)
                .orElseThrow(()-> new UsernameNotFoundException(nick + " no encontrado"));
    }


    public Optional<com.salesianos.dam.anuel.MiarmaNetwork.users.model.User> getUser(UUID id){
        return userRepository.findById(id);
    }

    public User save(CreateUserDto userDto, MultipartFile file){

        //TODO añadir excepciones

        if (userRepository.existsByNick(userDto.getNick())){
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
        } else{

        }


    }












    }

