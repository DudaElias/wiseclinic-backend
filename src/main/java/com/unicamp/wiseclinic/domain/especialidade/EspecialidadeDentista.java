package com.unicamp.wiseclinic.domain.especialidade;

import java.util.Arrays;

public enum EspecialidadeDentista implements Especialidade {
    ORTODONTIA(240),
    PERIODONTIA(247),
    ODONTOPEDIATRIA(236);

    private final int cod;

    EspecialidadeDentista(int especialidade){
        cod = especialidade;
    }

    public int getCod(){
        return cod;
    }

    @Override
    public Especialidade getEspecialidadePorCod(int cod) throws Exception {
        return Arrays.stream(EspecialidadeMedica.values())
                .filter(e -> e.getCod() == cod)
                .findFirst()
                .orElseThrow(() -> new Exception());
    }
}
