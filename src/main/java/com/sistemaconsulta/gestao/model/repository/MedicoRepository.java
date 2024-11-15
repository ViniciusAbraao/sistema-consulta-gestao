package com.sistemaconsulta.gestao.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaconsulta.gestao.model.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

}
