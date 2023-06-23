package com.unicamp.wiseclinic.application.consulta;

import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.consulta.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaServiceImpl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public Consulta criarConsulta(CriarConsultaCommand criarConsultaCommand) {
        // Adicionar logica
        Consulta consulta = criarConsultaCommand.toConsulta();
        consultaRepository.salvar(consulta);

        return consulta;
    }

    @Override
    public List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception {
        return consultaRepository.getConsultasPorDia(dia);
    }

    @Override
    public void checkInPaciente(int id) throws Exception {
        consultaRepository.checkInPaciente(id);
    }

    @Override
    public Consulta deleteConsulta(int id) throws Exception{
        return consultaRepository.deleteConsulta(id);
    }
}
