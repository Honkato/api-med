package ETSMed.API.model.paciente;

import ETSMed.API.model.endereco.Endereco;

public record PacienteRecordList(
        int id,
        String nome,
        String telefone,
        String email,
        String cpf,
        Endereco endereco,
        boolean ativo
) {
    public PacienteRecordList(Paciente paciente) {
        this(   paciente.getId(),
                paciente.getNome(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getEndereco(),
                paciente.isAtivo());
    }
}
