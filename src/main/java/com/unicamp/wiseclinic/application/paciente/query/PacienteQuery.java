package com.unicamp.wiseclinic.application.paciente.query;

import com.unicamp.wiseclinic.domain.convenio.Convenio;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record PacienteQuery(
    String cpf,
    String nome,
    String endereco,
    String email,
    String telefone,
    String genero,
    LocalDate dataNascimento,
    Convenio convenio
) {

    public static PacienteQuery toQuery(Paciente paciente) {
        return new PacienteQuery(
            paciente.getCpf(),
            paciente.getNome(),
            paciente.getEndereco(),
            paciente.getEmail(),
            paciente.getTelefone(),
            paciente.getGenero(),
            paciente.getDataNascimento(),
            paciente.getConvenio()
        );
    }
}
