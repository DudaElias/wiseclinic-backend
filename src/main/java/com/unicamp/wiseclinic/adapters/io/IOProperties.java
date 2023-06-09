package com.unicamp.wiseclinic.adapters.io;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wiseclinic.database", ignoreUnknownFields = true)
public record IOProperties(
    String paciente,
    String dentista,
    String consulta,
    String medico
) { }
