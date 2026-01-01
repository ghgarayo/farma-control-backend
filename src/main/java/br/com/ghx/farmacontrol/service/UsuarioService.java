package br.com.ghx.farmacontrol.service;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;
import br.com.ghx.farmacontrol.dto.usuario.UsuarioInputDTO;

public interface UsuarioService {

    UsuarioEntity create(UsuarioInputDTO dto);
    UsuarioEntity activate(String usuarioId, String activationCode);
}
