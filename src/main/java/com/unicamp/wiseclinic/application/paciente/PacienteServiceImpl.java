package com.unicamp.wiseclinic.application.paciente;

import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.paciente.PacienteRepository;
import com.unicamp.wiseclinic.domain.paciente.exception.PacienteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente getPacientePorDocumento(String documento) throws Exception {
        return Optional.ofNullable(pacienteRepository.getPacientePorDocumento(documento)).orElseThrow(() -> {
            return new PacienteNotFoundException(documento);
        });
    }
}
