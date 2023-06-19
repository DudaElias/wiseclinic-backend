package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.dentista.DentistaService;
import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    private DentistaService dentistaService;

    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping(value = "/{especialidade}")
    List<Dentista> criarConsulta(@PathVariable("especialidade") EspecialidadeDentista especialidadeDentista) throws IOException {
        return dentistaService.getDentistasPorEspecialidade(especialidadeDentista);
    }
}
