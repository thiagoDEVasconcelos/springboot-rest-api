package med.voll.api.domain.consulta;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public void agendar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidationException("ID do médico informado não existe.");
        }
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidationException("ID do paciente informado não existe.");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            var medico = medicoRepository.getReferenceById(dados.idMedico());
            return medico;
        }

        if(dados.especialidade() == null) {
            throw new ValidationException("Especificar a especialidade é obrigatória quando médico não for escolhido.");
        }

        return medicoRepository.escolherMedicoLivreAleatorioNaData(dados.especialidade(), dados.data());
    }
}
