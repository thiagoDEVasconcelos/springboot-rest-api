package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {
    @Autowired
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDoHorarioDeAbertura = dataConsulta.getHour() < 7;
        var depoisDoHorarioDeEncerramento = dataConsulta.getHour() > 18;

        if(domingo || antesDoHorarioDeAbertura || depoisDoHorarioDeEncerramento) {
            throw new ValidationException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
