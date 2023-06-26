package com.unicamp.wiseclinic.domain.consulta;

import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.profissional.Profissional;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Consulta {
    protected UUID id;
    protected Paciente paciente;
    protected LocalDateTime horario;
    protected boolean checkIn;

    public Consulta(UUID id, Paciente paciente, LocalDateTime horario, boolean checkIn) {
        this.id = id;
        this.paciente = paciente;
        this.horario = horario;
        this.checkIn = checkIn;
    }
    
    public UUID getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public abstract void trocarProfissional(Profissional profissional);

    public void trocarHorario(LocalDateTime horario){

    }

    public void checkInPaciente() {
        checkIn = checkIn ? false : true;
    }

}
