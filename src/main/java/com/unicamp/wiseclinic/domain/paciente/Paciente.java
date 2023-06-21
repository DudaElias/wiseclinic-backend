package com.unicamp.wiseclinic.domain.paciente;

import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.convenio.Convenio;
import com.unicamp.wiseclinic.domain.gerenciadorConsulta.GerenciadorConsulta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paciente implements GerenciadorConsulta {
    protected final String cpf;
    protected String nome;
    protected String endereco;
    protected String email;
    protected String telefone;
    protected String genero;
    protected LocalDate dataNascimento;
    protected Convenio convenio;
    protected List<Consulta> listaConsulta;

    public Paciente(String cpf) {
        this.cpf = cpf;
    }

    public Paciente(String cpf, String nome, String endereco, String email, String telefone, String genero, LocalDate dataNascimento, Convenio convenio) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.convenio = convenio;
        listaConsulta = new ArrayList<Consulta>();
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public List<Consulta> getListaConsulta() {
        return listaConsulta;
    }

    public boolean agendarConsulta(Consulta consulta){
        return true;
    }

    public boolean cancelarConsulta(int id){
        return true;
    }

}
