package com.unicamp.wiseclinic.domain.especialidade.exception;

public sealed class EspecialidadeException extends Exception
    permits AreaNotFoundException, EspecialidadeNotAvailableException, EspecialidadeNotFoundException
{
    public EspecialidadeException(String message) {
        super(message);
    }
}
