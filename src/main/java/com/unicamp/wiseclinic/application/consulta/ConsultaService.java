package com.unicamp.wiseclinic.application.consulta;

import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.domain.consulta.Consulta;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaService {

    Consulta criarConsulta(CriarConsultaCommand criarConsultaCommand);

    List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception;

    void checkInPaciente(int id) throws Exception;

    Consulta deleteConsulta(int id) throws Exception;
}
