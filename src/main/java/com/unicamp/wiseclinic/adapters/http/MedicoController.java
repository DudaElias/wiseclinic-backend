package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.domain.medico.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping(value = "/agenda/{crm}")
    List<LocalDateTime> getHorariosDisponiveis(@PathVariable("crm") String crm) throws Exception {
        try {
            return medicoService.getHorariosDisponiveis(crm);
        }catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }
}
