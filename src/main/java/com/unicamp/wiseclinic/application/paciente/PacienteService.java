package com.unicamp.wiseclinic.application.paciente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unicamp.wiseclinic.domain.paciente.Paciente;

public interface PacienteService {

    Paciente getPacientePorDocumento(String documento) throws Exception;
}
