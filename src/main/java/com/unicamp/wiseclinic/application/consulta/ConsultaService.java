package com.unicamp.wiseclinic.application.consulta;

import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.domain.consulta.Consulta;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ConsultaService {

    Consulta criarConsulta(CriarConsultaCommand criarConsultaCommand) throws Exception;

    List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception;

    void checkInPaciente(UUID id) throws Exception;

    Consulta deleteConsulta(UUID id) throws Exception;
}
