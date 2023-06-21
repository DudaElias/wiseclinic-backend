package com.unicamp.wiseclinic.domain.paciente.exception;

public sealed class PacienteException extends Exception permits PacienteNotFoundException {

    public PacienteException(String message) {
        super(message);
    }
}
