package com.unicamp.wiseclinic.domain.especialidade.exception;

public final class EspecialidadeNotAvailableException extends EspecialidadeException {

    private final String especialidade;
    private final String area;
    public EspecialidadeNotAvailableException(String area, String especialidade) {
        super(String.format("A especialidade %s não está disponível para a área %s.", especialidade, area));
        this.especialidade = especialidade;
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public String getEspecialidade() {
        return especialidade;
    }
}
