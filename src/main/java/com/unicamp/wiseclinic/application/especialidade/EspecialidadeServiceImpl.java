package com.unicamp.wiseclinic.application.especialidade;

import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.especialidade.exception.ReferenciaNotFoundException;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.paciente.PacienteRepository;
import com.unicamp.wiseclinic.domain.paciente.exception.PacienteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {

    List<Class<? extends Especialidade>> especialidades = Arrays.asList(
        EspecialidadeDentista.class,
        EspecialidadeMedica.class
    );

    @Override
    public List<? extends Especialidade> getEspecialidadesPorReferencia(String referencia) throws Exception {
        return Arrays.stream(especialidades.stream()
            .filter(especialidade -> {
                try {
                    return Objects.equals(referencia, extractReferencia(especialidade));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .findFirst().orElseThrow(() -> {
                return new ReferenciaNotFoundException(referencia);
            })
            .getEnumConstants()).toList();
    }

    private String extractReferencia(Class<? extends  Especialidade> especialidade) {
        return especialidade.getEnumConstants()[0].getReferencia();
    }
}
