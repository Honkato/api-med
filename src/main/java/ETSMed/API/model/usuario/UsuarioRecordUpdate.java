package ETSMed.API.model.usuario;

import ETSMed.API.model.endereco.EnderecoRecord;
import jakarta.validation.constraints.NotNull;

public record UsuarioRecordUpdate(
        @NotNull int id,
        String nome
) {

}
