package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.dentista.DentistaRepository;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
    public List<Dentista> getDentistas(EspecialidadeDentista especialidadeDentista) throws IOException {
        return Arrays
            .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.dentista()), Dentista[].class))
            .filter(dentista -> dentista.getEspecialidades().contains(especialidadeDentista))
            .toList();
    }

    @Override
    public List<LocalDateTime> getHorariosDisponiveis(String cro) throws Exception{
        List<Dentista> dentistas = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.dentista()), Dentista[].class));
        for(Dentista dentista : dentistas){
            if(dentista.getCro().equals(cro)){
                return dentista.getAgenda().getHorariosDisponiveis();
            }
        }
        throw new Exception();

    }


}
