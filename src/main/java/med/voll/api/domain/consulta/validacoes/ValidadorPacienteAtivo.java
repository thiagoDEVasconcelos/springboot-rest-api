package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteAtivo {
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idMedico());
        if (!pacienteEstaAtivo) {
            throw new ValidationException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
