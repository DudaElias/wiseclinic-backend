package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.paciente.PacienteService;
import com.unicamp.wiseclinic.application.paciente.query.PacienteQuery;
import com.unicamp.wiseclinic.domain.paciente.exception.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping(value = "/{cpf}")
    PacienteQuery getPacientePorDocumento(@PathVariable("cpf") String documento) {
        try {
            return PacienteQuery.toQuery(pacienteService.getPacientePorDocumento(documento));
        } catch (PacienteNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }
}
