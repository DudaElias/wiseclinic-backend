package com.unicamp.wiseclinic.application.medico;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public interface MedicoService {

    public Map<LocalDateTime, Integer> getHorariosDisponiveis(String crm) throws IOException;
}
