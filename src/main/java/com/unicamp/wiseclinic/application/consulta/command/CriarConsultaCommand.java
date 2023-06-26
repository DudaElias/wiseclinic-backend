package com.unicamp.wiseclinic.application.consulta.command;

import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;

import java.time.LocalDateTime;

public record CriarConsultaCommand(

    Paciente paciente,
    LocalDateTime data,
    Profissional profissional
) {
    public record Paciente(
        String cpf
    ) {}

    public record Profissional(
        String area,
        String especialidade,
        String documento
    ) {}
}
