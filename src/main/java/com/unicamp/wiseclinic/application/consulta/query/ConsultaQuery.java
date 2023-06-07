package com.unicamp.wiseclinic.application.consulta.query;

import com.unicamp.wiseclinic.domain.consulta.Consulta;

public class ConsultaQuery {

    public ConsultaQuery() {

    }

    public static ConsultaQuery toQuery(Consulta consulta) {
        return new ConsultaQuery();
    }
}
