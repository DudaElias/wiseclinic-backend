package com.unicamp.wiseclinic.domain.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Agenda {
    private Map<LocalDateTime, Integer> horariosDisponiveis;

    public Agenda() {
        this.horariosDisponiveis = Collections.emptyMap();
    }

    public Map<LocalDateTime, Integer> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void liberarHorario(LocalDateTime horario) {
        horariosDisponiveis.remove(horario);
    }

    public void ocuparHorario(LocalDateTime horario, int id) {
        horariosDisponiveis.put(horario, id);
    }

    public boolean isHorarioOcupado(LocalDateTime horario) {
        return horariosDisponiveis.containsKey(horario);
    }

    public Map<LocalDateTime, Integer> filtrarHorario(LocalDate dia) {
        return horariosDisponiveis.entrySet().stream()
            .filter(entry -> entry.getKey().toLocalDate().equals(dia))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}