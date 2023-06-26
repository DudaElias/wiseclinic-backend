package com.unicamp.wiseclinic.application.consulta.query;

import com.unicamp.wiseclinic.application.paciente.query.PacienteQuery;
import com.unicamp.wiseclinic.domain.consulta.Consulta;

public record ConsultaQuery(
    PacienteQuery paciente
) {

    /* public static ConsultaQuery toQuery(Consulta consulta) {
        return new ConsultaQuery(
            new PacienteQuery(
                consulta.getPaciente().getCpf(),
                consulta.getPaciente().getNome(),
                consulta.getPaciente().getEndereco(),
                consulta.getPaciente().getEmail(),
                consulta.getPaciente().getTelefone(),
                consulta.getPaciente().getGenero(),
                consulta.getPaciente().getDataNascimento(),
                consulta.getPaciente().getConvenio()
            )
        );
    } */
}
