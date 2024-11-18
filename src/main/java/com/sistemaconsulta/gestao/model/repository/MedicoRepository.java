package com.sistemaconsulta.gestao.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaconsulta.gestao.model.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
	List<Medico> findByEspecialidadeId(Long especialidadeId);

	Optional<Medico> findById(Medico medico);
}
