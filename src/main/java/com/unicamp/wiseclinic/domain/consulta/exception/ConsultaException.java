package com.unicamp.wiseclinic.domain.consulta.exception;

public sealed class ConsultaException extends Exception permits ConsultaNotFoundException {
    private final int id;

    public ConsultaException(int id, String message) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
