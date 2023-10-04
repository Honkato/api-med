package ETSMed.API.model.usuario;

import ETSMed.API.model.endereco.Endereco;
import ETSMed.API.model.paciente.Paciente;

public record UsuarioRecordList(
        int id,
        String nome,
        String login,
        String senha
) {
    public UsuarioRecordList(Usuario usuario) {
        this(   usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha());
    }
}
