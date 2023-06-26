package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.medico.MedicoRepository;
import com.unicamp.wiseclinic.domain.profissional.Medico;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@EnableConfigurationProperties(IOProperties.class)
public class MedicoIORepository implements MedicoRepository {

    private final ObjectMapper objectMapper;
    private final IOProperties ioProperties;

    public MedicoIORepository(
        ObjectMapper objectMapper,
        IOProperties ioProperties
    ) {
        this.objectMapper = objectMapper;
        this.ioProperties = ioProperties;
    }

    @Override
    public Medico getProfissionalPorDocumento(String crm) throws Exception {
        return Arrays
            .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.medico()), Medico[].class))
            .filter(medico -> Objects.equals(medico.getCrm(), crm))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<LocalDateTime> getHorariosDisponiveis(String crm) throws Exception {
        List<Medico> medicos = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.medico()), Medico[].class));
        for(Medico medico : medicos){
            if(medico.getCrm().equals(crm)){
                return medico.getAgenda().getHorariosDisponiveis();
            }
        }

        throw new Exception();
    }

    @Override
    public List<? extends Profissional> getProfissionaisPorEspecialidade(Especialidade especialidade) throws Exception {
        return Arrays
            .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.dentista()), Medico[].class))
            .filter(medico -> medico.getEspecialidades().contains(especialidade))
            .toList();
    }
}
