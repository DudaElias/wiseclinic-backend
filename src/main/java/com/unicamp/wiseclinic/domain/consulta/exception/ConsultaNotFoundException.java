package com.unicamp.wiseclinic.domain.consulta.exception;

public final class ConsultaNotFoundException extends ConsultaException {

    public ConsultaNotFoundException(int id) {
        super(id, String.format("A consulta %s não foi encontrada.", id));
    }
}
