package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.application.consulta.query.ConsultaQuery;
import com.unicamp.wiseclinic.application.consulta.ConsultaService;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping(value = "/")
    ConsultaQuery criarConsulta(@RequestBody CriarConsultaCommand criarConsultaCommand){
        try {
            return ConsultaQuery.toQuery(consultaService.criarConsulta(criarConsultaCommand));
        }
        catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }

    @GetMapping(value = "/{dia}")
    List<Consulta> getConsultasPorDia(@PathVariable("dia") LocalDate dia) {
        try {
            return consultaService.getConsultasPorDia(dia);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }

    @PutMapping(value = "/checkIn/{id}")
    void checkInPaciente(@PathVariable("id") UUID id) {
        try {
            consultaService.checkInPaciente(id);
        }
        catch(Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }
    
    @DeleteMapping(value="/{id}")
    Consulta deleteConsultaPorId(@PathVariable("id") UUID id) {
        try {
            return consultaService.deleteConsulta(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }

}
