package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.application.consulta.query.ConsultaQuery;
import com.unicamp.wiseclinic.application.consulta.ConsultaService;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping(value = "/")
    ConsultaQuery criarConsulta(@RequestBody CriarConsultaCommand criarConsultaCommand) {
        Consulta consulta = consultaService.criarConsulta(criarConsultaCommand);

        return ConsultaQuery.toQuery(consulta);
    }

    @GetMapping(value = "/{horario}")
    List<Consulta> getConsultasPorHorario(@PathVariable("horario") LocalDateTime horario) {
        try {
            return consultaService.getConsultasPorHorario(horario);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }

    @PutMapping(value = "/checkIn/{id}")
    void checkInPaciente(@PathVariable("id") int id) {
        try {
            consultaService.checkInPaciente(id);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }

}
