package com.unicamp.wiseclinic.domain.especialidade;

public enum EspecialidadeMedica implements Especialidade {
    ALERGOLOGIA(197),
    CARDIOLOGIA(200),
    DERMATOLOGIA(421),
    NEUROLOGIA(766),
    OFTALMOLOGIA(502),
    PEDIATRIA(707);

    private static final String REFERENCIA = "MEDICINA";

    private final int cod;

    EspecialidadeMedica(int especialidade){
        cod = especialidade;
    }

    public int getCod(){
        return cod;
    }

    public String getReferencia() {
        return REFERENCIA;
    }
}
