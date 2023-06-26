package com.unicamp.wiseclinic.domain.profissional;

import com.unicamp.wiseclinic.domain.especialidade.Especialidade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProfissionalRepository {
    Profissional getProfissionalPorDocumento(String documento) throws Exception;

    List<LocalDateTime> getHorariosDisponiveis(String documento, LocalDate data) throws Exception;

    List<? extends Profissional> getProfissionaisPorEspecialidade(Especialidade especialidade) throws Exception;

    void atualizarProfissional(Profissional profissional) throws Exception;

}
