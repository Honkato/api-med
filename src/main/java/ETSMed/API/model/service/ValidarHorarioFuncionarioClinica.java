package ETSMed.API.model.service;

import ETSMed.API.model.consulta.ConsultaRecordAgendamento;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionarioClinica implements ValidarAgendamentoDeConsultas{
    public void validar(ConsultaRecordAgendamento consultaRecordAgendamento){
        var dataColsulta = consultaRecordAgendamento.data();
        var domingo = dataColsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDeAberturaDaClinica = dataColsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataColsulta.getHour() > 18;
        if (domingo || antesDeAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new RuntimeException("Consulta fora do horario de funcionamento da clinica");
        }

    }

}
