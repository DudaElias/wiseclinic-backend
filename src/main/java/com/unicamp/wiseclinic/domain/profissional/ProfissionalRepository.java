package com.unicamp.wiseclinic.domain.profissional;

import com.unicamp.wiseclinic.domain.especialidade.Especialidade;

import java.time.LocalDateTime;
import java.util.List;

public interface ProfissionalRepository {
    Profissional getProfissionalPorDocumento(String documento) throws Exception;

    List<LocalDateTime> getHorariosDisponiveis(String documento) throws Exception;

    List<? extends Profissional> getProfissionaisPorEspecialidade(Especialidade especialidade) throws Exception;
}
