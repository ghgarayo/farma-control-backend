package br.com.ghx.farmacontrol.dto.authentication;

import br.com.ghx.farmacontrol.dto.usuario.UsuarioOutputDTO;

public record AuthenticationResponseDTO(String token, UsuarioOutputDTO usuario) {
}
