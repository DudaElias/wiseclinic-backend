package com.unicamp.wiseclinic.domain.especialidade;

import com.unicamp.wiseclinic.application.dentista.DentistaServiceImpl;
import com.unicamp.wiseclinic.application.medico.MedicoServiceImpl;
import com.unicamp.wiseclinic.domain.profissional.ProfissionalService;

import java.util.Arrays;

public enum Area {
    MEDICINA(EspecialidadeMedica.class),
    ODONTOLOGIA(EspecialidadeDentista.class);

    private Class<? extends Especialidade> especialidades;

    Area(Class<? extends Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public Class<? extends Especialidade> getEspecialidades() {
        return this.especialidades;
    }

    public Especialidade getEspecialidadePorCod(int code) {
        return Arrays.stream(this.especialidades.getEnumConstants())
            .filter(e -> e.getCod() == code)
            .findFirst()
            .orElseThrow();
    }
}
