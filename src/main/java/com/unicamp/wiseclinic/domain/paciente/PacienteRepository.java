package com.unicamp.wiseclinic.domain.paciente;

public interface PacienteRepository {
    Paciente getPacientePorDocumento(String documento) throws Exception;
}
