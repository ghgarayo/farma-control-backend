package br.com.ghx.farmacontrol.controller;

import br.com.ghx.farmacontrol.dto.authentication.AuthenticationRequestDTO;
import br.com.ghx.farmacontrol.dto.authentication.AuthenticationResponseDTO;
import br.com.ghx.farmacontrol.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid AuthenticationRequestDTO dto) {
        var responseDTO = authenticationService.authenticate(dto.username(), dto.password());

        return ResponseEntity.ok(responseDTO);
    }

}
