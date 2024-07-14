package com.estrella.foro_hub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.estrella.foro_hub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;
    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro-hub")
                    .withSubject(usuario.getUsuario())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token no puede ser nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("foro-hub")
                    .build();
            DecodedJWT jwt = ((JWTVerifier) verifier).verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido", exception);
        }
    }

//    public Object getSubject(String token) {
//        if(token == null){
//            throw new RuntimeException();
//        }
//        DecodedJWT verifier = null;
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //Validamos la firma con el tipo de algoritmo
//            verifier = JWT.require(algorithm)
//                    .withIssuer("foro-hub")
//                    .build()
//                    .verify(token);
//            verifier.getSubject();
//        } catch (JWTVerificationException exception) {
//            System.out.println(exception.toString());
//        }
//        if(verifier.getSubject() == null) { //Cuando no nos devulva el subject
//            throw new RuntimeException("Verifier invalido");
//        }
//        return verifier.getSubject();
//    }
}