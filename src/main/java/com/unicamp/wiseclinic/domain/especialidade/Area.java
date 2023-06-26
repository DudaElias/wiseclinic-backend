package com.unicamp.wiseclinic.domain.especialidade;

import com.unicamp.wiseclinic.application.dentista.DentistaServiceImpl;
import com.unicamp.wiseclinic.application.medico.MedicoServiceImpl;
import com.unicamp.wiseclinic.domain.profissional.ProfissionalService;

public enum Area {
    MEDICINA(EspecialidadeMedica.class, MedicoServiceImpl.class),
    ODONTOLOGIA(EspecialidadeDentista.class, DentistaServiceImpl.class);

    private Class<? extends Especialidade> especialidades;

    private Class<? extends ProfissionalService> profissionalService;

    Area(Class<? extends Especialidade> especialidades, Class<? extends ProfissionalService> profissionalService) {
        this.especialidades = especialidades;
        this.profissionalService = profissionalService;
    }

    public Class<? extends Especialidade> getEspecialidades() {
        return this.especialidades;
    }

    public Class<? extends ProfissionalService> getProfissionalService() {
        return profissionalService;
    }
}
