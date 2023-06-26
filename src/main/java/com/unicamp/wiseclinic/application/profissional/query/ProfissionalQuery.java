package com.unicamp.wiseclinic.application.profissional.query;

import com.unicamp.wiseclinic.domain.profissional.Profissional;

import java.time.LocalDate;

public record ProfissionalQuery(
    String nome,
    String cpf,
    String email
) {

    public static ProfissionalQuery toQuery(Profissional profissional) {
        return new ProfissionalQuery(
            profissional.getNome(),
            profissional.getCpf(),
            profissional.getEmail()
        );
    }
}
