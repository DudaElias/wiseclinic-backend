package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicamp.wiseclinic.adapters.io.ClasspathUtils;
import com.unicamp.wiseclinic.adapters.io.IOProperties;
import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.medico.Medico;
import com.unicamp.wiseclinic.domain.medico.MedicoRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@EnableConfigurationProperties(IOProperties.class)
public class MedicoIORepository implements MedicoRepository {

    private ObjectMapper objectMapper;
    private IOProperties ioProperties;

    public MedicoIORepository(
            ObjectMapper objectMapper,
            IOProperties ioProperties
    ) {
        this.objectMapper = objectMapper;
        this.ioProperties = ioProperties;
    }

    @Override
    public List<Medico> getMedicos(EspecialidadeMedica especialidadeMedica) throws IOException {
        return Arrays
                .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.medico()), Medico[].class))
                .filter(medico -> medico.getEspecialidades().contains(especialidadeMedica))
                .toList();
    }


}
