package com.unicamp.wiseclinic.application.consulta;

import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.domain.consulta.Consulta;

public interface ConsultaService {

    Consulta criarConsulta(CriarConsultaCommand criarConsultaCommand);
}
