package com.unicamp.wiseclinic.domain.dentista;

import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;

import java.io.IOException;
import java.util.List;

public interface DentistaRepository {
    List<Dentista> getDentistas(EspecialidadeDentista especialidadeDentista) throws IOException;
}
