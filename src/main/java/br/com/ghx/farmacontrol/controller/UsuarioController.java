package br.com.ghx.farmacontrol.controller;

import br.com.ghx.farmacontrol.dto.usuario.ActivationCodeInputDTO;
import br.com.ghx.farmacontrol.dto.usuario.UsuarioInputDTO;
import br.com.ghx.farmacontrol.dto.usuario.UsuarioOutputDTO;
import br.com.ghx.farmacontrol.infra.config.AuditorAwareImpl;
import br.com.ghx.farmacontrol.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final AuditorAwareImpl auditorAware;

    @PostMapping
    public ResponseEntity<UsuarioOutputDTO> createUser(@RequestBody UsuarioInputDTO data, UriComponentsBuilder builder) {
        var user = service.create(data);
        var uri = builder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioOutputDTO(user));
    }

    @PatchMapping("/activate/{usuarioId}")
    public ResponseEntity<String> activateUser(@PathVariable String usuarioId, @RequestBody ActivationCodeInputDTO request) {
        var activatedUser = service.activate(usuarioId, request.activationCode());

        String auditor = auditorAware.getCurrentAuditor()
                .map(a -> a.getUsername())
                .orElse("Unknown");

        String message = String.format("User %s activated user %s", auditor, activatedUser.getUsername());

        log.info(message);

        return ResponseEntity.ok(message);
    }


}