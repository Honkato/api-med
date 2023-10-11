package ETSMed.API.model.consulta;

import java.time.LocalDateTime;

public record ConsultaRecordDetalhamento(
        int id,
        int idMedico,
        int idPaciente,
        LocalDateTime data
) {
    public ConsultaRecordDetalhamento(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getData()
        );

    }
}
