package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;


@ConfigurationProperties(prefix = "app.data")
public record DataProperties(
  String path,
  String format,
  @DefaultValue(",") String delimiter,
  @DefaultValue("true") boolean skipHeader,
  @DefaultValue("UTF-8") String encoding,
  String label
) {}
  

