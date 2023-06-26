package com.unicamp.wiseclinic.domain.especialidade.exception;

public final class AreaNotFoundException extends EspecialidadeException {
    private final String area;
    public AreaNotFoundException(String area) {
        super(String.format("A área %s não foi encontrada.", area));
        this.area = area;
    }

    public String getArea() {
        return area;
    }
}
