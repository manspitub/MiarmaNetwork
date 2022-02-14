package com.salesianos.dam.anuel.MiarmaNetwork.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "storage")
@Getter @Setter
public class StorageProperties {

    private String location;

}
