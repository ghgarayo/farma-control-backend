package br.com.ghx.farmacontrol.dto.usuario;

import br.com.ghx.farmacontrol.enumeration.EnumRoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UsuarioInputDTO(
        @NotNull @Email String email,
        @NotNull String password,
        @NotNull EnumRoleType role)  {
}
