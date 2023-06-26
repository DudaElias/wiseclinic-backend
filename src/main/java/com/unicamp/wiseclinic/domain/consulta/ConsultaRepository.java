package com.unicamp.wiseclinic.domain.consulta;

import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.profissional.Profissional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ConsultaRepository {

    Consulta salvar(
        UUID id,
        LocalDateTime horario,
        boolean checkIn,
        Profissional profissional,
        Especialidade especialidade,
        Paciente paciente
    ) throws Exception;

    List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception;

    void checkInPaciente(UUID id) throws Exception;

    Consulta deleteConsulta(UUID id) throws Exception;
}
