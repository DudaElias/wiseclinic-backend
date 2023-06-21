package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.application.consulta.query.ConsultaQuery;
import com.unicamp.wiseclinic.application.consulta.ConsultaService;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
