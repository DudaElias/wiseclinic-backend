package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.dentista.DentistaRepository;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class DentistaIORepository implements DentistaRepository {

    private ObjectMapper objectMapper;

    public DentistaIORepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Dentista> getDentistas(EspecialidadeDentista especialidadeDentista) throws IOException {
        List<Dentista> dentistas =  Arrays.asList(objectMapper.readValue(
            ClasspathUtils.readFromClasspath("files/dentista.json"), Dentista[].class)
        );

        return dentistas.stream()
            .filter(dentista -> dentista.getEspecialidades().contains(especialidadeDentista))
            .toList();
    }


}
