package br.com.ghx.farmacontrol.service.impl;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;
import br.com.ghx.farmacontrol.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    public static final String OFFSET_ID = "-03:00";
    public static final int HALF_DAY_IN_HOURS = 12;

    // TODO: Move to constants class
    public static final String AUTHENTICATION_SERVICE = "Authentication Service";
    public static final String ERRO_AO_GERAR_TOKEN = "Erro ao gerar token";
    public static final String INVALID_OR_EXPIRED_JWT_TOKEN = "Token JWT inválido ou expirado!";

    @Value("${api.security.token.secret}")
    private String secret;

    @Override
    public String generateToken(UsuarioEntity user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(AUTHENTICATION_SERVICE)
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(ERRO_AO_GERAR_TOKEN, exception);
        }
    }

    @Override
    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Authentication Service")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTCreationException exception) {
            throw new RuntimeException(INVALID_OR_EXPIRED_JWT_TOKEN);
        }
    }


    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(HALF_DAY_IN_HOURS).toInstant(ZoneOffset.of(OFFSET_ID));
    }
}
