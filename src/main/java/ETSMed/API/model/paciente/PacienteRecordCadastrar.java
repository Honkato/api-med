package ETSMed.API.model.paciente;

import ETSMed.API.model.endereco.EnderecoRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteRecordCadastrar(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String telefone,
        @NotNull @Valid
        EnderecoRecord endereco
) {
}
