package com.unicamp.wiseclinic.domain.paciente.exception;

public final class PacienteNotFoundException extends PacienteException {

    private final String cpf;
    public PacienteNotFoundException(String cpf) {
        super(String.format("O paciente com documento %s não foi encontrado.", cpf));
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
