package com.unicamp.wiseclinic.application.dentista;

import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.dentista.DentistaRepository;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DentistaServiceImpl implements DentistaService {
    private final DentistaRepository dentistaRepository;

    public DentistaServiceImpl(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Override
    public List<Dentista> getDentistasPorEspecialidade(EspecialidadeDentista especialidadeDentista) throws IOException {
        return dentistaRepository.getDentistas(especialidadeDentista);
    }

    @Override
    public Map<LocalDateTime, Integer> getHorariosDisponiveis(String cro) throws Exception{
        return dentistaRepository.getHorariosDisponiveis(cro);
    }
}
