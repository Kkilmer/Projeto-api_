package br.com.alunoonline.api.infra.security;

import br.com.alunoonline.api.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String gerarToken(Usuario usuario) {

        //se for valido, retorna o login do usuario
        //se for invalido ou expirado, lança a exceção

        try {
            var algoritmo  = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.med")//define quem criou o token
                    .withSubject(usuario.getLogin())//login -> user
                    .withExpiresAt(dataExpiracao())//dafine a validade
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw  new RuntimeException(
                    "Erro ao gerar o token jwt ", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusYears(1).
                toInstant(ZoneOffset.of("-03:00"));
    }


}
