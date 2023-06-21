package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.paciente.PacienteRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
@EnableConfigurationProperties(IOProperties.class)
public class PacienteIORepository implements PacienteRepository {

    private ObjectMapper objectMapper;
    private IOProperties ioProperties;

    public PacienteIORepository(
        ObjectMapper objectMapper,
        IOProperties ioProperties
    ) {
        this.objectMapper = objectMapper;
        this.ioProperties = ioProperties;
    }

    @Override
    public Paciente getPacientePorDocumento(String documento) throws JsonProcessingException {
        return Arrays
            .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.paciente()), Paciente[].class))
            .filter(paciente -> Objects.equals(paciente.getCpf(), documento))
            .findFirst()
            .orElse(null);
    }
}
