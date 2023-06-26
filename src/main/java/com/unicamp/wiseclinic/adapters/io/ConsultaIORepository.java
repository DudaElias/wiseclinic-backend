package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.unicamp.wiseclinic.adapters.io.models.ConsultaIO;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.consulta.ConsultaRepository;
import com.unicamp.wiseclinic.domain.dentista.DentistaRepository;
import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.medico.MedicoRepository;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.paciente.PacienteRepository;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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

    private DentistaRepository dentistaIORepository;
    private PacienteRepository pacienteIORepository;
    private MedicoRepository medicoIORepository;

    @Autowired
    public ConsultaIORepository(
        ObjectMapper objectMapper,
        IOProperties ioProperties,
        DentistaRepository dentistaIORepository,
        PacienteRepository pacienteIORepository,
        MedicoRepository medicoIORepository
    ) {
        this.objectMapper = objectMapper;
        this.ioProperties = ioProperties;
        this.dentistaIORepository = dentistaIORepository;
        this.medicoIORepository = medicoIORepository;
        this.pacienteIORepository = pacienteIORepository;
    }

    @Override
    public Consulta salvar(UUID id, LocalDateTime horario, boolean checkIn, Profissional profissional,
                           Area area, int especialidade, Paciente paciente) throws Exception {
        List<Consulta> consultas = new ArrayList<>(getConsultas());

        Consulta novaConsulta = new Consulta(id, horario, checkIn, profissional, area, especialidade, paciente);
        consultas.add(novaConsulta);

        save(consultas);
        return novaConsulta;
    }

    @Override
    public List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception {
        return getConsultas()
            .stream()
            .filter(consulta -> consulta.getHorario().toLocalDate().equals(dia))
            .toList();
    }

    @Override
    public void checkInPaciente(UUID id) throws Exception {
        List<Consulta> consultas = getConsultas();

        Consulta consulta = consultas
            .stream()
            .filter(consul -> consul.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> { return new Exception(); });
        consulta.checkInPaciente();

        save(consultas);
    }

    @Override
    public Consulta deleteConsulta(UUID id) throws Exception{
        List<Consulta> consultas = new ArrayList<>(getConsultas());

        Consulta deletedConsulta = consultas
                .stream()
                .filter(consul -> consul.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> { return new Exception(); });

        consultas.remove(deletedConsulta);
        save(consultas);
        return deletedConsulta;
    }

    private List<Consulta> getConsultas() throws JsonProcessingException {
        List<ConsultaIO> consultasIO = Arrays.asList(objectMapper.readValue(
            ClasspathUtils.readFromClasspath(ioProperties.consulta()),
            ConsultaIO[].class)
        );

        return consultasIO
            .stream()
            .map(c -> {
                Paciente paciente = null;
                try {
                     paciente = pacienteIORepository.getPacientePorDocumento(c.docPaciente());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                Profissional profissional = null;
                try {
                     profissional = switch (c.area()) {
                        case MEDICINA -> medicoIORepository.getProfissionalPorDocumento(c.codProfissional());
                        case ODONTOLOGIA -> dentistaIORepository.getProfissionalPorDocumento(c.codProfissional());
                    };
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


                return ConsultaIO.toConsulta(c, paciente, profissional);
            }).toList();
    }

    private void save(List<Consulta> consultas) throws URISyntaxException, IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(
            new File(ClassLoader.getSystemResource(ioProperties.consulta()).toURI()),
            toConsultasIO(consultas)
        );
    }

    private List<ConsultaIO> toConsultasIO(List<Consulta> consultas) {
        return consultas
            .stream()
            .map(ConsultaIO::toConsultaIO)
            .toList();
    }
}
