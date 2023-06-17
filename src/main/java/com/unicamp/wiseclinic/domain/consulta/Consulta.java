package com.unicamp.wiseclinic.domain.consulta;

import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.profissional.Profissional;

import java.time.LocalDateTime;

public abstract class Consulta {
    protected int id;
    protected Paciente paciente;
    protected LocalDateTime horario;
    protected boolean checkIn;

    public int getId() {
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

    public void checkInPaciente(){
        checkIn = true;
    }

}
