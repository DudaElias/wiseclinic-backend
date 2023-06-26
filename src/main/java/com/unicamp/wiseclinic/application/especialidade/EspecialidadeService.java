package com.unicamp.wiseclinic.application.especialidade;

import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;

import java.util.List;

public interface EspecialidadeService {

    List<? extends Especialidade> getEspecialidadesPorArea(Area area) throws Exception;
}
