package br.com.ghx.farmacontrol.service.impl;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;
import br.com.ghx.farmacontrol.dto.authentication.AuthenticationResponseDTO;
import br.com.ghx.farmacontrol.dto.usuario.UsuarioOutputDTO;
import br.com.ghx.farmacontrol.repository.UsuarioRepository;
import br.com.ghx.farmacontrol.service.AuthenticationService;
import br.com.ghx.farmacontrol.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    public AuthenticationResponseDTO authenticate(String username, String password) {
        var authenticationRequest = new UsernamePasswordAuthenticationToken(username, password);
        var authentication = authenticationManager.authenticate(authenticationRequest);

        return usuarioRepository.findByUsernameAndActiveIsTrue(username)
                .map(usuarioEntity -> {
                    var token = tokenService.generateToken((UsuarioEntity) authentication.getPrincipal());
                    var usuarioOutputDTO = new UsuarioOutputDTO(usuarioEntity);
                    return new AuthenticationResponseDTO(token, usuarioOutputDTO);
                }
        ).orElseThrow(() -> new RuntimeException("Usuário não encontrado ou inativo")
        );
    }
}
