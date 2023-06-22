package com.unicamp.wiseclinic.application.medico;

import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.dentista.DentistaRepository;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.medico.Medico;
import com.unicamp.wiseclinic.domain.medico.MedicoRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


import java.time.LocalDateTime;
import java.util.Map;

@Service
public class MedicoServiceImpl implements MedicoService{
    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public List<Medico> getMedicosPorEspecialidade(EspecialidadeMedica especialidadeMedica) throws IOException {
        return medicoRepository.getMedicos(especialidadeMedica);
    }

    @Override
    public Map<LocalDateTime, Integer> getHorariosDisponiveis(String crm) throws Exception {
        return medicoRepository.getHorariosDisponiveis(crm);
    }
}
