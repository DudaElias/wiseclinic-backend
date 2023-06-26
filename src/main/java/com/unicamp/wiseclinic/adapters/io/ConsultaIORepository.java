package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.consulta.ConsultaRepository;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class ConsultaIORepository implements ConsultaRepository {
    private ObjectMapper objectMapper;
    private IOProperties ioProperties;

    public ConsultaIORepository(ObjectMapper objectMapper, IOProperties ioProperties) {
        this.objectMapper = objectMapper;
        this.ioProperties = ioProperties;
    }

    @Override
    public Consulta salvar(UUID id, LocalDateTime horario, boolean checkIn, Profissional profissional,
                           Especialidade especialidade, Paciente paciente) throws Exception {
        return null;
    }

    /* private ConsultaOdontologica salvarConsultaOdontologica(
        UUID id,
        LocalDateTime horario,
        boolean checkIn,
        Dentista dentista,
        EspecialidadeDentista especialidade,
        Paciente paciente
    ) throws Exception {
        List<ConsultaOdontologica> consultasOdontologica = getConsultasOdontologicas();

        ConsultaOdontologica consultaOdontologica = new ConsultaOdontologica(
            dentista,
            especialidade,
            id,
            paciente,
            horario,
            checkIn
        );

        consultasOdontologica.add(consultaOdontologica);

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(
            new File(ClassLoader.getSystemResource(ioProperties.consultaOdontologica()).toURI()),
            consultasOdontologica
        );

        return consultaOdontologica;
    }

    private ConsultaMedica salvarConsultaMedica(
        UUID id,
        LocalDateTime horario,
        boolean checkIn,
        Medico medico,
        EspecialidadeMedica especialidade,
        Paciente paciente
    ) throws Exception {
        List<ConsultaMedica> consultasMedicas = getConsultasMedicas();

        ConsultaMedica consultaMedica = new ConsultaMedica(
            medico,
            especialidade,
            id,
            paciente,
            horario,
            checkIn
        );

        consultasMedicas.add(consultaMedica);

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(
            new File(ClassLoader.getSystemResource(ioProperties.consultaMedica()).toURI()),
            consultasMedicas
        );

        return consultaMedica;
    } */

    @Override
    public List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception {
        List<Consulta> consultas = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consulta()), Consulta[].class));

        return consultas.stream()
                .filter(consulta -> consulta.getHorario().toLocalDate().equals(dia))
                .toList();
    }

    @Override
    public void checkInPaciente(UUID id) throws Exception {
        List<Consulta> consultas = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consulta()), Consulta[].class));
        Consulta consulta = consultas
            .stream()
            .filter(consul -> consul.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> { return new Exception(); });
        consulta.checkInPaciente();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(ClassLoader.getSystemResource(ioProperties.consulta()).toURI()), consultas);
    }

    @Override
    public Consulta deleteConsulta(UUID id) throws Exception{
        List<Consulta> consultas = new ArrayList<>(Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consulta()), Consulta[].class)));

        Consulta deletedConsulta = consultas
                .stream()
                .filter(consul -> consul.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> { return new Exception(); });

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        consultas.remove(deletedConsulta);
        writer.writeValue(new File(ClassLoader.getSystemResource(ioProperties.consulta()).toURI()), consultas);

        return deletedConsulta;
    }

    /*
    @Override
    public Consulta salvar(UUID id, LocalDateTime horario, boolean checkIn, String codProfissional, Area area,
            Especialidade especialidade, String docPaciente) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    } */
}
