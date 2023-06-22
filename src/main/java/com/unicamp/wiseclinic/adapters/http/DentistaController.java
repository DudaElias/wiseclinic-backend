package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.dentista.DentistaService;
import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    private DentistaService dentistaService;

    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping(value = "/{especialidade}")
    List<Dentista> getDentistaPorEspecialidade(@PathVariable("especialidade") EspecialidadeDentista especialidadeDentista) throws IOException {
        return dentistaService.getDentistasPorEspecialidade(especialidadeDentista);
    }

    @GetMapping(value = "/agenda/{cro}")
    Map<LocalDateTime, Integer> getHorariosDisponiveis(@PathVariable("cro") String cro) throws Exception {
        return dentistaService.getHorariosDisponiveis(cro);
    }
}
