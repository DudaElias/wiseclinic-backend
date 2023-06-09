package com.unicamp.wiseclinic.domain.profissional;

import com.unicamp.wiseclinic.domain.agenda.Agenda;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.gerenciadorConsulta.GerenciadorConsulta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract sealed class Profissional implements GerenciadorConsulta permits Dentista, Medico {
    private Agenda agenda;
    private String nome;
    private String cpf;
    private String email;
    private List<Consulta> listaConsulta;

    public Profissional() {

    }

    public Profissional(Agenda agenda, String nome, String cpf, String email) {
        this.agenda = agenda;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        listaConsulta = new ArrayList<Consulta>();
    }

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

    public Agenda getAgenda() { return agenda; }

    public abstract boolean agendarConsulta(Consulta consulta);

    public abstract String getDoc();
    
    public boolean cancelarConsulta(UUID id) {
        return this.listaConsulta.removeIf(consulta -> {
            return consulta.getId() == id;
        });
    }
}
