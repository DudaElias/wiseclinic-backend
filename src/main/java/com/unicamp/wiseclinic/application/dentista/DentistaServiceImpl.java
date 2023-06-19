package com.unicamp.wiseclinic.application.dentista;

import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.dentista.DentistaRepository;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DentistaServiceImpl implements DentistaService{
    private final DentistaRepository dentistaRepository;

    public DentistaServiceImpl(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Override
    public List<Dentista> getDentistasPorEspecialidade(EspecialidadeDentista especialidadeDentista) throws IOException {
        return dentistaRepository.getDentistas(especialidadeDentista);
    }
}
