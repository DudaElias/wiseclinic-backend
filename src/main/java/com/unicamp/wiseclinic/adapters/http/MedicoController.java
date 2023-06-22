package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.dentista.DentistaService;
import com.unicamp.wiseclinic.application.medico.MedicoService;
import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import com.unicamp.wiseclinic.application.medico.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;
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

    @GetMapping(value = "/{especialidade}")
    List<Medico> getMedicosPorEspecialidade(@PathVariable("especialidade") EspecialidadeMedica especialidadeMedica) throws IOException {
        return medicoService.getMedicosPorEspecialidade(especialidadeMedica);
    }
    
    @GetMapping(value = "/agenda/{crm}")
    Map<LocalDateTime, Integer> getHorariosDisponiveis(@PathVariable("crm") String crm) throws Exception {
        try {
            return medicoService.getHorariosDisponiveis(crm);
        }catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }
}
