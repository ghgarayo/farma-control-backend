package br.com.ghx.farmacontrol.controller;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;
import br.com.ghx.farmacontrol.dto.authentication.AuthenticationRequestDTO;
import br.com.ghx.farmacontrol.dto.authentication.AuthenticationResponseDTO;
import br.com.ghx.farmacontrol.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid AuthenticationRequestDTO dto) {
        var authenticationRequest = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var authentication = authenticationManager.authenticate(authenticationRequest);
        var token = tokenService.generateToken((UsuarioEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }

}
