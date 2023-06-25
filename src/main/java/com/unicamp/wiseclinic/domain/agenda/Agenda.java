package com.unicamp.wiseclinic.domain.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Agenda {
    private List<LocalDateTime> horariosDisponiveis;

    public Agenda() {
        this.horariosDisponiveis = Collections.emptyList();
    }


    public List<LocalDateTime> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void liberarHorario(LocalDateTime horario) {
        horariosDisponiveis.add(horario);
    }

    public void ocuparHorario(LocalDateTime horario) {
        horariosDisponiveis.remove(horario);
    }

    public boolean isHorarioOcupado(LocalDateTime horario) {
        return horariosDisponiveis.contains(horario);
    }

    public List<LocalDateTime> filtrarHorario(LocalDate dia) {
        return horariosDisponiveis.stream()
                .filter(dateTime -> dateTime.toLocalDate().equals(dia))
                .collect(Collectors.toList());
    }
}