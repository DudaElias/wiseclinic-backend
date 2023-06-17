package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.consulta.DentistaService;
import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.application.consulta.query.ConsultaQuery;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    private DentistaService dentistaService;

    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping(value = "/")
    DentistaQuery criarConsulta(@RequestBody CriarConsultaCommand criarConsultaCommand) {
        Consulta consulta = dentistaService.criarConsulta(criarConsultaCommand);

        return ConsultaQuery.toQuery(consulta);
    }
}
