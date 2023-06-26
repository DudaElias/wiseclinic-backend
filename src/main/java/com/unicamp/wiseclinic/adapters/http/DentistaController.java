package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.domain.dentista.DentistaService;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    private DentistaService dentistaService;

    @Autowired
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping(value = "/{especialidade}")
    List<? extends Profissional> getDentistasPorEspecialidade(@PathVariable("especialidade") EspecialidadeDentista especialidadeDentista) throws Exception {
        return dentistaService.getProfissionaisPorEspecialidade(especialidadeDentista);
    }

    @GetMapping(value = "/agenda/{cro}/{data}")
    List<LocalDateTime> getHorariosDisponiveis(@PathVariable("cro") String cro, @PathVariable("data") LocalDate data) throws Exception {
        return dentistaService.getHorariosDisponiveis(cro, data);
    }
}
