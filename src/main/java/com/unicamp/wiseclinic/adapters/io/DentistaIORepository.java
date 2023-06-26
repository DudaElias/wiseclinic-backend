package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.unicamp.wiseclinic.domain.dentista.DentistaRepository;
import com.unicamp.wiseclinic.domain.profissional.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public void atualizarProfissional(Profissional profissional) throws Exception {
        List<Dentista> dentistas = Arrays
                .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.dentista()), Dentista[].class))
                .filter(dentista -> dentista.getCro() != ((Dentista) profissional).getCro())
                .toList();
        dentistas.add(((Dentista) profissional));

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(ClassLoader.getSystemResource(ioProperties.dentista()).toURI()), dentistas);
    }

    @Override
    public List<LocalDateTime> getHorariosDisponiveis(String cro, LocalDate data) throws Exception{
        List<Dentista> dentistas = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.dentista()), Dentista[].class));
        for(Dentista dentista : dentistas){
            if(dentista.getCro().equals(cro)){
                List<LocalDateTime> horariosOcupados = dentista.getAgenda().filtrarHorario(data);

                LocalDateTime startDateTime = LocalDateTime.of(data, LocalTime.of(8, 0));
                LocalDateTime endDateTime = LocalDateTime.of(data, LocalTime.of(17, 0));
                LocalDateTime horarioAlmoco = LocalDateTime.of(data, LocalTime.of(12, 0));

                List<LocalDateTime> horariosDisponiveis = new ArrayList<>();

                LocalDateTime currentDateTime = startDateTime;
                while (currentDateTime.isBefore(endDateTime)) {
                    if(!horariosOcupados.contains(currentDateTime) && !currentDateTime.equals(horarioAlmoco)){
                        horariosDisponiveis.add(currentDateTime);
                    }
                    currentDateTime = currentDateTime.plusHours(1);
                }

                return horariosDisponiveis;
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
