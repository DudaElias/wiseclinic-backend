package com.unicamp.wiseclinic.adapters.io;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.consulta.ConsultaMedica;
import com.unicamp.wiseclinic.domain.consulta.ConsultaOdontologica;
import com.unicamp.wiseclinic.domain.consulta.ConsultaRepository;

import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.medico.Medico;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
    public List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception {
        List<ConsultaMedica> consultasMedica = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consultaMedica()), ConsultaMedica[].class));
        List<ConsultaOdontologica> consultasOdontologica = Arrays.asList(objectMapper.readValue(ClasspathUtils.readFromClasspath(ioProperties.consultaOdontologica()), ConsultaOdontologica[].class));
        List<Consulta> consultas = Stream.concat(consultasMedica.stream(), consultasOdontologica.stream()).toList();

        return consultas.stream()
                .filter(consulta -> consulta.getHorario().toLocalDate().equals(dia))
                .toList();
    }

    @Override
    public void checkInPaciente(int id) throws Exception {
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
    public Consulta deleteConsulta(int id) throws Exception{
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

}
