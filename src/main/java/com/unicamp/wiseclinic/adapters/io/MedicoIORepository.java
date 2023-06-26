package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.medico.MedicoRepository;
import com.unicamp.wiseclinic.domain.profissional.Dentista;
import com.unicamp.wiseclinic.domain.profissional.Medico;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ArrayList;
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
    public List<LocalDateTime> getHorariosDisponiveis(String crm, LocalDate data) throws Exception {
        List<Medico> medicos = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.medico()), Medico[].class));
        for(Medico medico : medicos){
            if(medico.getCrm().equals(crm)){
                List<LocalDateTime> horariosOcupados = medico.getAgenda().filtrarHorario(data);

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
    public List<? extends Profissional> getProfissionaisPorEspecialidade(Especialidade especialidade) throws Exception {
        return Arrays
            .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.medico()), Medico[].class))
            .filter(medico -> medico.getEspecialidades().contains(especialidade))
            .toList();
    }

    @Override
    public void atualizarProfissional(Profissional profissional) throws Exception {
        List<Medico> medicos = new ArrayList<>(Arrays
                .stream(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.medico()), Medico[].class))
                .filter(medico -> !Objects.equals(medico.getCrm(), ((Medico) profissional).getCrm()))
                .toList());
        medicos.add(((Medico) profissional));

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(ClassLoader.getSystemResource(ioProperties.medico()).toURI()), medicos);
    }
}
