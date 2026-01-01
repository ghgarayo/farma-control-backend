package br.com.ghx.farmacontrol.dto.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequestDTO(
        @NotBlank @Email String username,
        @NotNull String password) {
}
