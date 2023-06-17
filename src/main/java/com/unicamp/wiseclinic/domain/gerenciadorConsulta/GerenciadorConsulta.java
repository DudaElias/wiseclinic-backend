package com.unicamp.wiseclinic.domain.gerenciadorConsulta;

import com.unicamp.wiseclinic.domain.consulta.Consulta;

public interface GerenciadorConsulta {
    public boolean agendarConsulta(Consulta consulta);
    public boolean cancelarConsulta(int id);
}
