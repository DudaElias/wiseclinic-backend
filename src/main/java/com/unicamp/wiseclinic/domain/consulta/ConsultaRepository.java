package com.unicamp.wiseclinic.domain.consulta;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository {

    void salvar(Consulta consulta);

    List<Consulta> getConsultasPorHorario(LocalDateTime horario) throws Exception;

    void checkInPaciente(int id) throws Exception;
}
