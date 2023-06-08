package com.unicamp.wiseclinic.domain.especialidade;

public enum EspecialidadeMedica {
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
}
