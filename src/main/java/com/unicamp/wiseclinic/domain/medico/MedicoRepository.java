package com.unicamp.wiseclinic.domain.medico;

import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;

import java.io.IOException;
import java.util.List;

public interface MedicoRepository {
    List<Medico> getMedicos(EspecialidadeMedica especialidadeMedica) throws IOException;

}
