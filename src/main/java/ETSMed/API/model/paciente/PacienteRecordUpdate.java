package ETSMed.API.model.paciente;

import ETSMed.API.model.endereco.EnderecoRecord;
import jakarta.validation.constraints.NotNull;

public record PacienteRecordUpdate(
        @NotNull int id,
        String nome,
        String telefone,
        EnderecoRecord endereco
) {

}
