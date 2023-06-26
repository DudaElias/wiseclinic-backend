package com.unicamp.wiseclinic.domain.consulta;

import com.unicamp.wiseclinic.domain.profissional.Medico;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import com.unicamp.wiseclinic.domain.paciente.Paciente;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConsultaMedica extends Consulta {
    private Medico medico;
    private EspecialidadeMedica especialidade;

    public ConsultaMedica(Medico medico, EspecialidadeMedica especialidade, UUID id, Paciente paciente, LocalDateTime horario, boolean checkIn) {
        super(id, paciente, horario, checkIn);
        this.medico = medico;
        this.especialidade = especialidade;
    }

    public Medico getMedico() {
        return medico;
    }

    public EspecialidadeMedica getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeMedica especialidade) {
        this.especialidade = especialidade;
    }

    public void trocarProfissional(Profissional profissional){
        if(profissional instanceof Medico){
            this.medico = (Medico) profissional;
        }
    }
}
