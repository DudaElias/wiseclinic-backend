package com.unicamp.wiseclinic.application.medico;


import com.unicamp.wiseclinic.domain.medico.MedicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class MedicoServiceImpl implements MedicoService{
    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Map<LocalDateTime, Integer> getHorariosDisponiveis(String crm) throws Exception {
        return medicoRepository.getHorariosDisponiveis(crm);
    }
}
