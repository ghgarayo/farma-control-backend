package br.com.ghx.farmacontrol.dto.usuario;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;

public record UsuarioOutputDTO(
        String username,
        boolean active
){

    public UsuarioOutputDTO(UsuarioEntity usuario){
        this(usuario.getUsername(), usuario.isActive());
    }
}
