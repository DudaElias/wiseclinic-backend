package com.unicamp.wiseclinic.domain.especialidade.exception;

public final class ReferenciaNotFoundException extends EspecialidadeException {

    private final String referencia;
    public ReferenciaNotFoundException(String referencia) {
        super(String.format("A referencia %s não foi encontrada.", referencia));
        this.referencia = referencia;
    }

    public String getReferencia() {
        return referencia;
    }
}
