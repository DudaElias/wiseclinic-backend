package com.unicamp.wiseclinic.domain.especialidade.exception;

public sealed class EspecialidadeException extends Exception permits ReferenciaNotFoundException {

    public EspecialidadeException(String message) {
        super(message);
    }
}
