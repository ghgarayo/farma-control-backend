package br.com.ghx.farmacontrol.dto.exception;

import java.time.Instant;

public record ExceptionDTO(
        String code,
        String message,
        int status,
        Instant timestamp,
        String path
) {
}
