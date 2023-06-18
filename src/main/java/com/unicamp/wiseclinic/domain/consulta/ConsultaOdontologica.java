package com.unicamp.wiseclinic.domain.consulta;

import com.unicamp.wiseclinic.domain.dentista.Dentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import com.unicamp.wiseclinic.domain.paciente.Paciente;

import java.time.LocalDateTime;

public class ConsultaOdontologica extends Consulta{
    private Dentista dentista;
    private EspecialidadeDentista especialidade;

    public ConsultaDentista(Dentista dentista, EspecialidadeDentista especialidade, int id, Paciente paciente, LocalDateTime horario, boolean checkIn) {
        super(id, paciente, horario, checkIn);
        this.dentista = dentista;
        this.especialidade = especialidade;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public EspecialidadeDentista getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeDentista especialidade) {
        this.especialidade = especialidade;
    }

    public void trocarProfissional(Profissional profissional){
        if(profissional instanceof Dentista){
            this.dentista = (Dentista) profissional;
        }
    }
}
