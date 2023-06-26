package com.unicamp.wiseclinic.application.consulta.query;

import com.unicamp.wiseclinic.application.paciente.query.PacienteQuery;
import com.unicamp.wiseclinic.application.profissional.query.ProfissionalQuery;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaQuery(
    UUID id,
    LocalDateTime horario,
    boolean checkin,
    ProfissionalQuery profissional,

    PacienteQuery paciente,

    Area area,
    Especialidade especialidade
) {

     public static ConsultaQuery toQuery(Consulta consulta) {
        return new ConsultaQuery(
            consulta.getId(),
            consulta.getHorario(),
            consulta.isCheckIn(),
            ProfissionalQuery.toQuery(consulta.getProfissional()),
            PacienteQuery.toQuery(consulta.getPaciente()),
            consulta.getArea(),
            consulta.getArea().getEspecialidadePorCod(consulta.getEspecialidade())
        );
    }
}
