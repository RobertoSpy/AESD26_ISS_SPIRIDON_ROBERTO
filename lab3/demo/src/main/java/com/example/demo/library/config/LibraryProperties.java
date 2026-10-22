package com.example.demo.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "library")
public record LibraryProperties(String borrowingPolicy, Integer highVolumeThreshold) {

    public LibraryProperties {
        if (borrowingPolicy == null) {
            borrowingPolicy = "fixed";
        }
        if (highVolumeThreshold == null) {
            highVolumeThreshold = 3;
        }
    }
}
