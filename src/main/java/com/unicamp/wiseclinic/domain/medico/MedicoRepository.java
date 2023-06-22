package com.unicamp.wiseclinic.domain.medico;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public interface MedicoRepository {
    Map<LocalDateTime, Integer> getHorariosDisponiveis(String crm) throws Exception;
}
