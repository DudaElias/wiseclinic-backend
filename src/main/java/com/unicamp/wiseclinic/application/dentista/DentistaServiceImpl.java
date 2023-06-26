package com.unicamp.wiseclinic.application.dentista;

import com.unicamp.wiseclinic.domain.dentista.DentistaRepository;
import com.unicamp.wiseclinic.domain.dentista.DentistaService;
import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.especialidade.exception.EspecialidadeNotAvailableException;
import com.unicamp.wiseclinic.domain.profissional.Dentista;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import com.unicamp.wiseclinic.domain.profissional.ProfissionalRepository;
import com.unicamp.wiseclinic.domain.profissional.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DentistaServiceImpl implements DentistaService {
    private final ProfissionalRepository dentistaRepository;

    @Autowired
    public DentistaServiceImpl(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Override
    public List<? extends Profissional> getProfissionaisPorEspecialidade(Especialidade especialidade) throws Exception {
        if (!(especialidade instanceof EspecialidadeDentista)) {
            throw new EspecialidadeNotAvailableException(Area.ODONTOLOGIA.name(), especialidade.toString());
        }
        return dentistaRepository.getProfissionaisPorEspecialidade(especialidade);
    }

    @Override
    public void removerConsulta(String codProfissional, LocalDateTime horario) throws Exception {
        Profissional dentista = getProfissionalPorDocumento(codProfissional);
        dentista.getAgenda().liberarHorario(horario);
        dentistaRepository.atualizarProfissional(dentista);
    }

    @Override
    public List<LocalDateTime> getHorariosDisponiveis(String cro, LocalDate data) throws Exception{
        return dentistaRepository.getHorariosDisponiveis(cro, data);
    }

    @Override
    public Profissional getProfissionalPorDocumento(String documento) throws Exception {
        return dentistaRepository.getProfissionalPorDocumento(documento);
    }
}
