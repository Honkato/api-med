package ETSMed.API.model.medico;

import ETSMed.API.model.endereco.EnderecoRecord;
import jakarta.validation.constraints.NotNull;

public record MedicoRecordUpdate(
        @NotNull int id,
        String nome,
        String telefone,
        EnderecoRecord endereco
) {

}
