package br.com.ghx.farmacontrol.service;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;

public interface TokenService {
    String generateToken(UsuarioEntity usuarioEntity);
    String getSubject(String tokenJwt);
}
