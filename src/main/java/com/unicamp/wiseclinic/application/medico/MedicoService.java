package com.unicamp.wiseclinic.application.medico;

import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.medico.Medico;

import java.io.IOException;
import java.util.List;

public interface MedicoService {

    List<Medico> getMedicosPorEspecialidade(EspecialidadeMedica especialidadeMedica) throws IOException;
}
