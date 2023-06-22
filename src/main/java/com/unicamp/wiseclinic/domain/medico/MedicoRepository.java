package com.unicamp.wiseclinic.domain.medico;

import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;

import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;

public interface MedicoRepository {
    List<Medico> getMedicos(EspecialidadeMedica especialidadeMedica) throws IOException;

    Map<LocalDateTime, Integer> getHorariosDisponiveis(String crm) throws Exception;
}
