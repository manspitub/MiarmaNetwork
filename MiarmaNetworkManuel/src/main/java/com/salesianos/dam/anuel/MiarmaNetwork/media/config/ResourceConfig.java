package com.salesianos.dam.anuel.MiarmaNetwork.media.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "storage")
@Getter @Setter

public class ResourceConfig {

    private String location;


}
