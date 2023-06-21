package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.consulta.ConsultaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ConsultaIORepository implements ConsultaRepository {
    private ObjectMapper objectMapper;
    private IOProperties ioProperties;

    public ConsultaIORepository(ObjectMapper objectMapper, IOProperties ioProperties) {
        this.objectMapper = objectMapper;
        this.ioProperties = ioProperties;
    }

    @Override
    public void salvar(Consulta consulta) {

    }

    @Override
    public List<Consulta> getConsultasPorHorario(LocalDateTime horario) throws Exception {
        List<Consulta> consultas = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consulta()), Consulta[].class));

        return consultas.stream()
                .filter(consulta -> consulta.getHorario().equals(horario))
                .toList();
    }

}
