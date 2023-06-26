package com.unicamp.wiseclinic.domain.especialidade.exception;

public final class EspecialidadeNotFoundException extends EspecialidadeException {

    private final String especialidade;
    private final String area;
    public EspecialidadeNotFoundException(String area, String especialidade) {
        super(String.format("A especialidade %s não foi encontrada para a área %s.", especialidade, area));
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
