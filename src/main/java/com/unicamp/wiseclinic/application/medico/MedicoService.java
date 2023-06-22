package com.unicamp.wiseclinic.application.medico;

import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.medico.Medico;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;


public interface MedicoService {

    List<Medico> getMedicosPorEspecialidade(EspecialidadeMedica especialidadeMedica) throws IOException;
    public Map<LocalDateTime, Integer> getHorariosDisponiveis(String crm) throws Exception;

}
