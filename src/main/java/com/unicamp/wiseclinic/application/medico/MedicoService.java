package com.unicamp.wiseclinic.application.medico;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface MedicoService {

    public List<LocalDateTime> getHorariosDisponiveis(String crm) throws Exception;
}
