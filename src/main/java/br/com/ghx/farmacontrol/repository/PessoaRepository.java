package br.com.ghx.farmacontrol.repository;


import br.com.ghx.farmacontrol.domain.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID> {

}
