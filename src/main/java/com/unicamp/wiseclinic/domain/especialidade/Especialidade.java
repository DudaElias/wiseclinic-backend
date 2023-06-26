package com.unicamp.wiseclinic.domain.especialidade;

public interface Especialidade {
    int getCod();
    Especialidade getEspecialidadePorCod(int cod) throws Exception;
}
