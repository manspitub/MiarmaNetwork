package com.salesianos.dam.anuel.MiarmaNetwork.media.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;


@ConfigurationProperties(prefix = "storage")
@Getter @Setter
public class StorageProperties {

    private String location;

    public StorageProperties(String location) {
        this.location = location;
    }
}
