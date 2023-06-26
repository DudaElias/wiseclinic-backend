package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.consulta.ConsultaMedica;
import com.unicamp.wiseclinic.domain.consulta.ConsultaOdontologica;
import com.unicamp.wiseclinic.domain.consulta.ConsultaRepository;

import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.profissional.Dentista;
import com.unicamp.wiseclinic.domain.profissional.Medico;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
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

    private ConsultaOdontologica salvarConsultaOdontologica(
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
    }

    @Override
    public List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception {
        List<ConsultaMedica> consultasMedica = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consultaMedica()), ConsultaMedica[].class));
        List<ConsultaOdontologica> consultasOdontologica = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consultaOdontologica()), ConsultaOdontologica[].class));
        List<Consulta> consultas = Stream.concat(consultasMedica.stream(), consultasOdontologica.stream()).toList();

        return consultas.stream()
                .filter(consulta -> consulta.getHorario().toLocalDate().equals(dia))
                .toList();
    }

    @Override
    public void checkInPaciente(UUID id) throws Exception {
        List<ConsultaMedica> consultasMedica = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consultaMedica()), ConsultaMedica[].class));
        List<ConsultaOdontologica> consultasOdontologica = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consultaOdontologica()), ConsultaOdontologica[].class));
        List<Consulta> consultas = Stream.concat(consultasMedica.stream(), consultasOdontologica.stream()).toList();
        Consulta consulta = consultas
            .stream()
            .filter(consul -> consul.getId() == id)
            .findFirst()
            .orElseThrow(() -> { return new Exception(); });
        consulta.checkInPaciente();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        if (consulta instanceof ConsultaMedica)
            writer.writeValue(new File(ClassLoader.getSystemResource(ioProperties.consultaMedica()).toURI()), consultasMedica);
        else if (consulta instanceof ConsultaOdontologica)
            writer.writeValue(new File(ClassLoader.getSystemResource(ioProperties.consultaOdontologica()).toURI()), consultasOdontologica);
    }

    @Override
    public Consulta deleteConsulta(UUID id) throws Exception{
        List<ConsultaMedica> consultasMedica = new ArrayList<>(Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consultaMedica()), ConsultaMedica[].class)));
        List<ConsultaOdontologica> consultasOdontologica = new ArrayList<>(Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consultaOdontologica()), ConsultaOdontologica[].class)));
        List<Consulta> consultas = Stream.concat(consultasMedica.stream(), consultasOdontologica.stream()).toList();

        Consulta deletedConsulta = consultas
                .stream()
                .filter(consul -> consul.getId() == id)
                .findFirst()
                .orElseThrow(() -> { return new Exception(); });

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        if (deletedConsulta instanceof ConsultaMedica) {
            Medico medico = ((ConsultaMedica) deletedConsulta).getMedico();
            medico.getAgenda().liberarHorario(deletedConsulta.getHorario());

            consultasMedica.remove(deletedConsulta);
            writer.writeValue(new File(ClassLoader.getSystemResource(ioProperties.consultaMedica()).toURI()), consultasMedica);
        }
        else if (deletedConsulta instanceof ConsultaOdontologica) {
            Dentista dentista = ((ConsultaOdontologica) deletedConsulta).getDentista();
            dentista.getAgenda().liberarHorario(deletedConsulta.getHorario());

            consultasOdontologica.remove(deletedConsulta);
            writer.writeValue(new File(ClassLoader.getSystemResource(ioProperties.consultaOdontologica()).toURI()), consultasOdontologica);
        }

        return deletedConsulta;
    }

    private ArrayList<ConsultaOdontologica> getConsultasOdontologicas() throws Exception {
        return new ArrayList<>(Arrays.asList(objectMapper.readValue(
            ClasspathUtils.readFromClasspath(ioProperties.consultaOdontologica()),
            ConsultaOdontologica[].class)
        ));
    }

    private ArrayList<ConsultaMedica> getConsultasMedicas() throws Exception {
        return new ArrayList<>(Arrays.asList(objectMapper.readValue(
            ClasspathUtils.readFromClasspath(ioProperties.consultaMedica()),
            ConsultaMedica[].class)
        ));
    }
}
