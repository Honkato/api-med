package ETSMed.API.model.service;

import ETSMed.API.model.consulta.ConsultaRecordAgendamento;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidarAgendamentoDeConsultas{

    public void validar(ConsultaRecordAgendamento consultaRecordAgendamento){
        var dataConsulta = consultaRecordAgendamento.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30){
            throw new RuntimeException("Consulta deve ser agendada com antecedencia de no minimo 30 minutos");
        }
    }
}
