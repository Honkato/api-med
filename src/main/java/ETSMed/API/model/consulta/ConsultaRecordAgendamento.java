package ETSMed.API.model.consulta;

import ETSMed.API.model.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRecordAgendamento(
        int idMedico,
        @NotNull
        int idPaciente,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade
) {
}
