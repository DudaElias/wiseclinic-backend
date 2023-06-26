package com.unicamp.wiseclinic.adapters.io.models;

import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.profissional.Profissional;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaIO(
    UUID id,
    LocalDateTime horario,
    boolean checkin,
    String codProfissional,

    String docPaciente,

    Area area,
    int especialidade
) {

    public static Consulta toConsulta(ConsultaIO consultaIO, Paciente paciente, Profissional profissional) {
        return new Consulta(
            consultaIO.id,
            consultaIO.horario,
            consultaIO.checkin,
            profissional,
            consultaIO.area,
            consultaIO.especialidade,
            paciente
        );
    }

    public static ConsultaIO toConsultaIO(Consulta consulta) {
        return new ConsultaIO(
            consulta.getId(),
            consulta.getHorario(),
            consulta.isCheckIn(),
            consulta.getProfissional().getDoc(),
            consulta.getPaciente().getCpf(),
            consulta.getArea(),
            consulta.getEspecialidade()
        );
    }
}