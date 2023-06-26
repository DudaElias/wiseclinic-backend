package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.medico.MedicoService;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
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

    @GetMapping(value = "/{especialidade}")
    List<? extends Profissional> getMedicosPorEspecialidade(@PathVariable("especialidade")
                                                            EspecialidadeMedica especialidadeMedica) throws Exception {
        return medicoService.getProfissionaisPorEspecialidade(especialidadeMedica);
    }

    @GetMapping(value = "/agenda/{crm}/{data}")
    List<LocalDateTime> getHorariosDisponiveis(@PathVariable("crm") String crm, @PathVariable("data") LocalDate data) throws Exception {
        try {
            return medicoService.getHorariosDisponiveis(crm, data);
        }catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }
}
