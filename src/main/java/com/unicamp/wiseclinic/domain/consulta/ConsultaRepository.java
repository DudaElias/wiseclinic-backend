package com.unicamp.wiseclinic.domain.consulta;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepository {

    void salvar(Consulta consulta);

    List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception;

    void checkInPaciente(int id) throws Exception;
}
