package com.unicamp.wiseclinic.application.consulta;

import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.domain.consulta.Consulta;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaService {

    Consulta criarConsulta(CriarConsultaCommand criarConsultaCommand);

    List<Consulta> getConsultasPorHorario(LocalDateTime horario) throws IOException;
}
