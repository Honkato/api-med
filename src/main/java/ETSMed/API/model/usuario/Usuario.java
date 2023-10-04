package ETSMed.API.model.usuario;

import ETSMed.API.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nome;
    String login;
    String senha;

    public Usuario(UsuarioRecordCadastrar UsuarioRecordCadastrar) {
        this.nome = UsuarioRecordCadastrar.nome();
        this.login = UsuarioRecordCadastrar.login();
        this.senha = UsuarioRecordCadastrar.senha();
    }

    public void atualizarInformacoes(UsuarioRecordUpdate UsuarioRecordUpdate) {
        if (UsuarioRecordUpdate.nome() != null){
            this.nome = UsuarioRecordUpdate.nome();
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
//    public void excluir(){this.ativo = false;}
}
