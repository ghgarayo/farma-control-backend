package br.com.ghx.farmacontrol.repository;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    Optional<UserDetails> findByUsername(String login);
    Optional<UsuarioEntity>  findByUsernameAndActiveIsTrue(String username);
    Optional<UsuarioEntity> findByIdAndActivationCode(UUID usuarioId, String activationCode);

}
