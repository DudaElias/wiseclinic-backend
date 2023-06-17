package com.unicamp.wiseclinic.domain.dentista;

import java.util.List;

import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;

public class Dentista {
    private String cro;
    private List<EspecialidadeDentista> especialidades;

    //construtor

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
}
