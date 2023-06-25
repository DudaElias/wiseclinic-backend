package com.unicamp.wiseclinic.domain.medico;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository {
    List<LocalDateTime> getHorariosDisponiveis(String crm) throws Exception;
}
