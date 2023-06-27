package com.unicamp.wiseclinic.domain.profissional;

import java.util.List;

import com.unicamp.wiseclinic.domain.agenda.Agenda;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;

public final class Dentista extends Profissional {
    private String cro;
    private List<EspecialidadeDentista> especialidades;

    public Dentista(String cro, Agenda agenda, String nome, String cpf, String email) {
        super(agenda, nome, cpf, email);
        this.cro = cro;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public List<EspecialidadeDentista> getEspecialidades() {
        return especialidades;
    }
    
    public void setEspecialidades(List<EspecialidadeDentista> especialidades) {
        this.especialidades = especialidades;
    }

    public void adicionarEspecialidade(EspecialidadeDentista especialidade) {
        especialidades.add(especialidade);
    }

    public boolean agendarConsulta(Consulta consulta) {
        return this.getListaConsulta().add(consulta);
    }

    @Override
    public String getDoc() {
        return getCro();
    }

    @Override
    public boolean cancelarConsulta(int id) {
        return false;
    }
}
