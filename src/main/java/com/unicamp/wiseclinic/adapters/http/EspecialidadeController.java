package com.unicamp.wiseclinic.adapters.http;

import com.unicamp.wiseclinic.application.especialidade.EspecialidadeService;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.especialidade.exception.ReferenciaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    private EspecialidadeService especialidadeService;

    @Autowired
    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @GetMapping(value = "/{especialidade}")
    List<? extends Especialidade> getEspecialidades(@PathVariable("especialidade") String especialidade) {
        try {
            return especialidadeService.getEspecialidadesPorReferencia(especialidade);
        } catch (ReferenciaNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno, tente novamente.", e);
        }
    }
}
