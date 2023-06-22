package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.dentista.DentistaService;
import com.unicamp.wiseclinic.application.medico.MedicoService;
import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    List<Medico> getMedicosPorEspecialidade(@PathVariable("especialidade") EspecialidadeMedica especialidadeMedica) throws IOException {
        return medicoService.getMedicosPorEspecialidade(especialidadeMedica);
    }
}
