package br.com.ghx.farmacontrol.infra.config;

import br.com.ghx.farmacontrol.domain.UsuarioEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {

    @Bean
    public AuditorAware<UsuarioEntity> auditorProvider() {
        return new AuditorAwareImpl();
    }
}