package com.unicamp.wiseclinic.application.dentista;

import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;

import java.io.IOException;
import java.util.List;

public interface DentistaService {

    List<Dentista> getDentistasPorEspecialidade(EspecialidadeDentista especialidadeDentista) throws IOException;
}
