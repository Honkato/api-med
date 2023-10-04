package ETSMed.API.model.usuario;

import ETSMed.API.model.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    UserDetails findByLogin(String username);
//    Page<Usuario> findAllByAtivoTrue(Pageable pageable);
}
