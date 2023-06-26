package com.unicamp.wiseclinic.domain.especialidade;

import java.util.Arrays;

public enum EspecialidadeMedica implements Especialidade {
    ALERGOLOGIA(197),
    CARDIOLOGIA(200),
    DERMATOLOGIA(421),
    NEUROLOGIA(766),
    OFTALMOLOGIA(502),
    PEDIATRIA(707);

    private final int cod;

    EspecialidadeMedica(int especialidade){
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
