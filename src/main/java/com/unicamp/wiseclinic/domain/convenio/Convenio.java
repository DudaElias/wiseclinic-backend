package com.unicamp.wiseclinic.domain.convenio;

public enum Convenio {
    SULAMERICA(101),
    AMIL(103),
    BRADESCO(107),
    UNIMED(109);

    private final int chave;

    Convenio(int convenio) {
        chave = convenio;
    }

    public int getChave() {
        return chave;
    }
}