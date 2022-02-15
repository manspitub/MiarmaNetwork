package com.salesianos.dam.anuel.MiarmaNetwork.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptPasswprdEncoderConfig {

    @Bean
    public BCryptPasswordEncoder passwprdEncoder(){
        return new BCryptPasswordEncoder();
    }

}
