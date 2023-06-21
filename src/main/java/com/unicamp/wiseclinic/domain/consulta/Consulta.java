package com.unicamp.wiseclinic.domain.consulta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.profissional.Profissional;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConsultaMedica.class, name = "ConsultaMedica"),
        @JsonSubTypes.Type(value = ConsultaOdontologica.class, name = "ConsultaOdontologica")
})
public abstract class Consulta {
    protected int id;
    protected Paciente paciente;
    protected LocalDateTime horario;
    protected boolean checkIn;

    public Consulta(int id, Paciente paciente, LocalDateTime horario, boolean checkIn) {
        this.id = id;
        this.paciente = paciente;
        this.horario = horario;
        this.checkIn = checkIn;
    }
    
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
