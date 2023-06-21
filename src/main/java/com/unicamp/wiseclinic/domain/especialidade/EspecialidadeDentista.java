package com.unicamp.wiseclinic.domain.especialidade;

public enum EspecialidadeDentista implements Especialidade {
    ORTODONTIA(240),
    PERIODONTIA(247),
    ODONTOPEDIATRIA(236);

    private static final String REFERENCIA = "ODONTOLOGIA";

    private final int cod;

    EspecialidadeDentista(int especialidade){
        cod = especialidade;
    }

    public int getCod(){
        return cod;
    }

    public String getReferencia() {
        return REFERENCIA;
    }
}
