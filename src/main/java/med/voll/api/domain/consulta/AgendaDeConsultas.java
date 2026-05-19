package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public AgendaDeConsultas(
            ConsultaRepository consultaRepository,
            MedicoRepository medicoRepository,
            PacienteRepository pacienteRepository
    ) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public void agendar(DadosAgendamentoConsulta dados) {
        var medico = medicoRepository.findById(dados.idMedico())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        var paciente = pacienteRepository.findById(dados.idPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }
}
