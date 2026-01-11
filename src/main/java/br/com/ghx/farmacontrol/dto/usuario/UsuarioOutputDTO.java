package br.com.ghx.farmacontrol.dto.usuario;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;
import br.com.ghx.farmacontrol.enumeration.EnumRoleType;

public record UsuarioOutputDTO(
        String id,
        String name,
        String email,
        EnumRoleType role,
        boolean active
){

    public UsuarioOutputDTO(UsuarioEntity usuario){
        this(
                usuario.getId() != null ? usuario.getId().toString() : null,
                usuario.getPerson() != null ? (
                        (usuario.getPerson().getFirstName() != null ? usuario.getPerson().getFirstName() : "")
                        + (usuario.getPerson().getLastName() != null ? " " + usuario.getPerson().getLastName() : "")
                ).trim() : null,
                usuario.getUsername(),
                usuario.getRole(),
                usuario.isActive()
        );
    }
}
