package com.unicamp.wiseclinic.domain.consulta;

import com.unicamp.wiseclinic.domain.especialidade.Area;

import java.time.LocalDateTime;
import java.util.UUID;

public class Consulta {
    private UUID id;
    private LocalDateTime horario;
    private boolean checkIn;
    private String codProfissional;
    private Area area;
    private int especialidade;
    private String docPaciente;

    public Consulta(UUID id, LocalDateTime horario, boolean checkIn, String codProfissional, Area area,
            int especialidade, String docPaciente) {
        this.id = id;
        this.horario = horario;
        this.checkIn = checkIn;
        this.codProfissional = codProfissional;
        this.area = area;
        this.especialidade = especialidade;
        this.docPaciente = docPaciente;
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

    public String getCodProfissional() {
        return codProfissional;
    }

    public void setCodProfissional(String codProfissional) {
        this.codProfissional = codProfissional;
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

    public String getDocPaciente() {
        return docPaciente;
    }

    public void setDocPaciente(String docPaciente) {
        this.docPaciente = docPaciente;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void checkInPaciente() {
        checkIn = checkIn ? false : true;
    }

}
