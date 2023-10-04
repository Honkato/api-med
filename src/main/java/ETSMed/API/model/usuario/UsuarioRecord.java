package ETSMed.API.model.usuario;

import ETSMed.API.model.endereco.Endereco;

public record UsuarioRecord(
        int id,
        String nome,
        String login,
        String senha
) {
    public UsuarioRecord(Usuario usuario) {
        this(   usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha());
    }
}
