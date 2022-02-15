package com.salesianos.dam.anuel.MiarmaNetwork.users.service.impl;

import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import com.salesianos.dam.anuel.MiarmaNetwork.users.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        log.info("Authentication request: {}", nick);
        final User user = userRepository.findByNick(nick);
        if (user == null){
            throw new UsernameNotFoundException("Profile is not found: " +nick);
        }

        log.info("Profile was found by nick: {}", nick);

        user.getFollowReceived().isEmpty();



    }
}
