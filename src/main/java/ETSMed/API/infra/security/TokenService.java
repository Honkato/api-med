package ETSMed.API.infra.security;

import ETSMed.API.model.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario){
        try{
            var algoritmo = Algorithm.HMAC256("123345678");
            return JWT.create()
                    .withSubject(usuario.getLogin())
                    .withIssuer("API")
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        }
        catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
