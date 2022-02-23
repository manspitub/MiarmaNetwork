package com.salesianos.dam.anuel.MiarmaNetwork.media.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "storage")
@Getter @Setter
public class ResourceConfig {

    private String location ;




}
