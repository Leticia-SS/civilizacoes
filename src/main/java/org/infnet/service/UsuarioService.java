package org.infnet.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.infnet.dto.UsuarioDTO;
import org.infnet.dto.UsuarioRecord;
import org.infnet.exception.AcessoInvalidoException;
import org.infnet.modelo.Usuario;
import org.infnet.repository.UsuarioRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private final UsuarioRepository repository;
    private final String SEGREDO = "ahduisahdiusahdiuashdiuashyu!";
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    public List<UsuarioRecord> getAll(){
        List<Usuario> usuarios = this.repository.listarTodos();

        return usuarios.stream()
                .map(Usuario::getUsuarioRecord)
                .toList();

    }
    public Usuario logar(Usuario usuario){
        Usuario retornado = this.repository
                .buscarPorLoginESenha(usuario.getLogin(), usuario.getSenha());
        if(retornado == null) throw new AcessoInvalidoException("Usuario ou Senha Incorretos");
        return retornado;
    }
    public String getTokenJWT(Usuario usuario){
        return JWT.create()
                .withSubject(usuario.getLogin())
                .withIssuer("ACME co")
                .withClaim("ROLE", "COMUM")
                .withExpiresAt(Instant.ofEpochSecond(System.currentTimeMillis() +( 1000)))
                .sign(Algorithm.HMAC256(SEGREDO));
    }
    public boolean validarToken(String token){
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(SEGREDO))
                .withIssuer("ACME co").build();
        try{

            DecodedJWT verify = verifier.verify(token);
            System.out.println(verify.getClaim("ROLE"));
            System.out.println(verify.getExpiresAt().toString());
        }catch (SignatureVerificationException ex){
            return false;
        }
        return true;
    }
}
