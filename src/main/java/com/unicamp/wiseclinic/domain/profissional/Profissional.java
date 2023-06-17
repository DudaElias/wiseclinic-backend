package com.unicamp.wiseclinic.domain.profissional;

import com.unicamp.wiseclinic.domain.agenda.Agenda;
import com.unicamp.wiseclinic.domain.consulta.Consulta;

import java.util.List;

public abstract class Profissional {
    private Agenda agenda;
    private String nome;
    private String cpf;
    private String email;
    private List<Consulta> listaConsulta;

    //construtor

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public List<Consulta> getListaConsulta() {
        return listaConsulta;
    }

    //public void atualizarAgenda(params);

    public abstract void agendarConsulta(Consulta consulta);
    public void cancelarConsulta(int id) {
        this.listaConsulta.removeIf(consulta -> {
            return consulta.
        })
    }
}
