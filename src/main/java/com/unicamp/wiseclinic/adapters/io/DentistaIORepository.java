package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicamp.wiseclinic.domain.dentista.DentistaRepository;
import com.unicamp.wiseclinic.domain.profissional.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@EnableConfigurationProperties(IOProperties.class)
public class DentistaIORepository implements DentistaRepository {

    private ObjectMapper objectMapper;
    private IOProperties ioProperties;

    public DentistaIORepository(
        ObjectMapper objectMapper,
        IOProperties ioProperties
    ) {
        this.objectMapper = objectMapper;
        this.ioProperties = ioProperties;
    }

    @Override
    public List<Dentista> getProfissionaisPorEspecialidade(Especialidade especialidadeDentista) throws Exception {
        return Arrays
            .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.dentista()), Dentista[].class))
            .filter(dentista -> dentista.getEspecialidades().contains(especialidadeDentista))
            .toList();
    }

    @Override
    public List<LocalDateTime> getHorariosDisponiveis(String cro) throws Exception {
        List<Dentista> dentistas = Arrays.asList(
            objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.dentista()), Dentista[].class)
        );

        for (Dentista dentista : dentistas) {
            if (dentista.getCro().equals(cro)) {
                return dentista.getAgenda().getHorariosDisponiveis();
            }
        }

        throw new Exception();
    }

    @Override
    public Profissional getProfissionalPorDocumento(String cro) throws Exception {
        return Arrays
            .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.dentista()), Dentista[].class))
            .filter(dentista -> Objects.equals(dentista.getCro(), cro))
            .findFirst()
            .orElse(null);
    }


}
