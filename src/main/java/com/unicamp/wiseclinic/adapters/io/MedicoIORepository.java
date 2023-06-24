package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.medico.Medico;
import com.unicamp.wiseclinic.domain.medico.MedicoRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;

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


    public Map<LocalDateTime, Integer> getHorariosDisponiveis(String crm) throws Exception {
        List<Medico> medicos = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.medico()), Medico[].class));
        for(Medico medico : medicos){
            if(medico.getCrm().equals(crm)){
                return medico.getAgenda().getHorariosDisponiveis();
            }
        }
        throw new Exception();

    }

}
