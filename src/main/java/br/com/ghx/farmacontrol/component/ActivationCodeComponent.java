package br.com.ghx.farmacontrol.component;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;
import br.com.ghx.farmacontrol.infra.exception.BusinessException;
import br.com.ghx.farmacontrol.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ActivationCodeComponent {

    private final UsuarioRepository usuarioRepository;

    public String generateActivationCode() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 6)
                .toUpperCase();
    }

    public void activateUser(UsuarioEntity usuario, String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Código inválido.");
        }

        if (!usuario.getActivationCode().equals(code)) {
            throw new BusinessException("Código de ativação incorreto.");
        }

        if (usuario.isActive()) {
            throw new BusinessException("Usuário já está ativo.");
        }

        usuario.setActive(true);
        usuarioRepository.saveAndFlush(usuario);
    }

}
