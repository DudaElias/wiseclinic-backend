package com.unicamp.wiseclinic.domain.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Agenda {
    private List<LocalDateTime> horariosOcupados;

    public Agenda() {
        this.horariosOcupados = Collections.emptyList();
    }


    public List<LocalDateTime> getHorariosOcupados() {
        return horariosOcupados;
    }

    public void liberarHorario(LocalDateTime horario) {
        horariosOcupados.remove(horario);
    }

    public void ocuparHorario(LocalDateTime horario) {
        horariosOcupados.add(horario);
    }

    public boolean isHorarioOcupado(LocalDateTime horario) {
        return horariosOcupados.contains(horario);
    }

    public List<LocalDateTime> filtrarHorario(LocalDate dia) {
        return horariosOcupados.stream()
                .filter(dateTime -> dateTime.toLocalDate().equals(dia))
                .collect(Collectors.toList());
    }
}