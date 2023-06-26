package com.unicamp.wiseclinic.domain.consulta;

import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.profissional.Profissional;

import java.time.LocalDateTime;
import java.util.UUID;

public class Consulta {
    private UUID id;
    private LocalDateTime horario;
    private boolean checkIn;
    private Profissional profissional;
    private Area area;
    private int especialidade;
    private Paciente paciente;

    public Consulta(UUID id, LocalDateTime horario, boolean checkIn, Profissional profissional, Area area,
            int especialidade, Paciente paciente) {
        this.id = id;
        this.horario = horario;
        this.checkIn = checkIn;
        this.profissional = profissional;
        this.area = area;
        this.especialidade = especialidade;
        this.paciente = paciente;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(int especialidade) {
        this.especialidade = especialidade;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void checkInPaciente() {
        checkIn = checkIn ? false : true;
    }

}
