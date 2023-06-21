package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.medico.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping(value = "/agenda/{crm}")
    Map<LocalDateTime, Integer> getHorariosDisponiveis(@PathVariable("crm") String crm) throws IOException {
        return medicoService.getHorariosDisponiveis(crm);
    }
}
