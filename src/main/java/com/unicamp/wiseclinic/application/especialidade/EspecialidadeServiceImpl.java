package com.unicamp.wiseclinic.application.especialidade;

import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {

    @Override
    public List<? extends Especialidade> getEspecialidadesPorArea(Area area) {
        return Arrays.stream(area.getEspecialidades().getEnumConstants()).toList();
    }
}
