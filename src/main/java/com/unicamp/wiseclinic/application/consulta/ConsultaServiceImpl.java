package com.unicamp.wiseclinic.application.consulta;

import com.unicamp.wiseclinic.application.consulta.command.CriarConsultaCommand;
import com.unicamp.wiseclinic.application.paciente.PacienteService;
import com.unicamp.wiseclinic.application.profissional.ProfissionalServiceFactory;
import com.unicamp.wiseclinic.domain.consulta.Consulta;
import com.unicamp.wiseclinic.domain.consulta.ConsultaRepository;
import com.unicamp.wiseclinic.domain.especialidade.Area;
import com.unicamp.wiseclinic.domain.especialidade.Especialidade;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeDentista;
import com.unicamp.wiseclinic.domain.especialidade.EspecialidadeMedica;
import com.unicamp.wiseclinic.domain.especialidade.exception.AreaNotFoundException;
import com.unicamp.wiseclinic.domain.especialidade.exception.EspecialidadeNotAvailableException;
import com.unicamp.wiseclinic.domain.paciente.Paciente;
import com.unicamp.wiseclinic.domain.profissional.Profissional;
import com.unicamp.wiseclinic.domain.profissional.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteService pacienteService;

    private final ProfissionalServiceFactory profissionalServiceFactory;

    @Autowired
    public ConsultaServiceImpl(
        ConsultaRepository consultaRepository,
        PacienteService pacienteService,
        ProfissionalServiceFactory profissionalServiceFactory
    ) {
        this.consultaRepository = consultaRepository;
        this.pacienteService = pacienteService;
        this.profissionalServiceFactory = profissionalServiceFactory;
    }

    @Override
    public Consulta criarConsulta(CriarConsultaCommand criarConsultaCommand) throws Exception {
        Area area = extractArea(criarConsultaCommand.profissional().area());
        ProfissionalService profissionalService = profissionalServiceFactory.getInstance(area);

        Paciente paciente = pacienteService.getPacientePorDocumento(criarConsultaCommand.paciente().cpf());

        Especialidade especialidade = extractEspecialidade(area, criarConsultaCommand.profissional().especialidade());

        Profissional profissional = profissionalService.getProfissionalPorDocumento(
            criarConsultaCommand.profissional().documento()
        );

        return consultaRepository.salvar(
            UUID.randomUUID(),
            criarConsultaCommand.data(),
            false,
            profissional,
            especialidade,
            paciente
        );
    }

    @Override
    public List<Consulta> getConsultasPorDia(LocalDate dia) throws Exception {
        return consultaRepository.getConsultasPorDia(dia);
    }

    @Override
    public void checkInPaciente(UUID id) throws Exception {
        consultaRepository.checkInPaciente(id);
    }

    @Override
    public Consulta deleteConsulta(UUID id) throws Exception{
        return consultaRepository.deleteConsulta(id);
    }

    private Area extractArea(String area) throws AreaNotFoundException {
        try {
            return Area.valueOf(area);
        } catch (Exception e) {
            throw new AreaNotFoundException(area);
        }
    }

    private Especialidade extractEspecialidade(Area area, String especialidade)
        throws EspecialidadeNotAvailableException {
        try {
            return switch (area) {
                case MEDICINA -> EspecialidadeMedica.valueOf(especialidade);
                case ODONTOLOGIA -> EspecialidadeDentista.valueOf(especialidade);
            };
        } catch (Exception e) {
            throw new EspecialidadeNotAvailableException(area.name(), especialidade);
        }
    }
}
