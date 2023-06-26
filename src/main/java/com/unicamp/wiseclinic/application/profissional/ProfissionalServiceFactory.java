package com.unicamp.wiseclinic.application.profissional;

import com.unicamp.wiseclinic.application.dentista.DentistaServiceImpl;
import com.unicamp.wiseclinic.application.medico.MedicoServiceImpl;
import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.profissional.ProfissionalRepository;
import com.unicamp.wiseclinic.domain.profissional.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalServiceFactory {

    private final MedicoServiceImpl medicoService;

    private final DentistaServiceImpl dentistaService;

    @Autowired
    public ProfissionalServiceFactory(MedicoServiceImpl medicoService, DentistaServiceImpl dentistaService) {
        this.medicoService = medicoService;
        this.dentistaService = dentistaService;
    }

    public ProfissionalService getInstance(Area area) {
        return switch (area) {
            case MEDICINA -> medicoService;
            case ODONTOLOGIA -> dentistaService;
        };
    }
}
