package com.unicamp.wiseclinic.domain.dentista;

import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface DentistaRepository {
    List<Dentista> getDentistas(EspecialidadeDentista especialidadeDentista) throws IOException;

    List<LocalDateTime> getHorariosDisponiveis(String cro) throws Exception;
}
