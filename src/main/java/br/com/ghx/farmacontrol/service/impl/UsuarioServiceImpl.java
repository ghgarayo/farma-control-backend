package br.com.ghx.farmacontrol.service.impl;

import br.com.ghx.farmacontrol.component.ActivationCodeComponent;
import br.com.ghx.farmacontrol.domain.UsuarioEntity;
import br.com.ghx.farmacontrol.dto.usuario.UsuarioInputDTO;
import br.com.ghx.farmacontrol.repository.UsuarioRepository;
import br.com.ghx.farmacontrol.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ActivationCodeComponent activationCodeComponent;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UsuarioEntity create(UsuarioInputDTO dto) {
        var encodedPassword = encoder.encode(dto.password());
        var activationCode = activationCodeComponent.generateActivationCode();

        var user = new UsuarioEntity(dto.email(), encodedPassword, dto.role(), activationCode);

        return usuarioRepository.saveAndFlush(user);
    }

    @Override
    public UsuarioEntity activate(String usuarioId, String activationCode) {
        var id = UUID.fromString(usuarioId);

        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        activationCodeComponent.activateUser(usuario, activationCode);

        return usuarioRepository.save(usuario);
    }
}
