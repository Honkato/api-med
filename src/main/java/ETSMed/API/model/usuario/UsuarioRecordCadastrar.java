package ETSMed.API.model.usuario;

import ETSMed.API.model.endereco.EnderecoRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioRecordCadastrar(
        @NotBlank
        String nome,
        @NotBlank @Email
        String login,
        @NotBlank
        String senha
) {
}
