package com.unicamp.wiseclinic.application.medico;


import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.especialidade.exception.EspecialidadeNotAvailableException;
import com.unicamp.wiseclinic.domain.medico.MedicoRepository;
import com.unicamp.wiseclinic.domain.medico.MedicoService;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import com.unicamp.wiseclinic.domain.profissional.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {
    private final ProfissionalRepository medicoRepository;

    @Autowired
    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public List<LocalDateTime> getHorariosDisponiveis(String crm, LocalDate data) throws Exception {
        return medicoRepository.getHorariosDisponiveis(crm, data);
    }

    @Override
    public List<? extends Profissional> getProfissionaisPorEspecialidade(Especialidade especialidade) throws Exception {
        if (!(especialidade instanceof EspecialidadeMedica)) {
            throw new EspecialidadeNotAvailableException(Area.MEDICINA.name(), especialidade.toString());
        }
        return medicoRepository.getProfissionaisPorEspecialidade(especialidade);
    }

    @Override
    public Profissional getProfissionalPorDocumento(String documento) throws Exception {
        return medicoRepository.getProfissionalPorDocumento(documento);
    }
}
