package com.unicamp.wiseclinic.domain.profissional;

import com.unicamp.wiseclinic.domain.agenda.Agenda;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.profissional.Profissional;

import java.util.List;

public final class Medico extends Profissional {
    private String crm;
    private List<EspecialidadeMedica> especialidades;

    public Medico(String crm) {
        super();
        this.crm = crm;
    }

    public Medico(String crm, Agenda agenda, String nome, String cpf, String email){
        super(agenda, nome, cpf, email);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<EspecialidadeMedica> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadeMedica> especialidades) {
        this.especialidades = especialidades;
    }

    public void adicionarEspecialidade(EspecialidadeMedica especialidade) {
        especialidades.add(especialidade);
    }

    public boolean agendarConsulta(Consulta consulta){
        //TODO: IMPLEMENTAR METODO
        return true;
    }

    @Override
    public boolean cancelarConsulta(int id) {
        return false;
    }
}
