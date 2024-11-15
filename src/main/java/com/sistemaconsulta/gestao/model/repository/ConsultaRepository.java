package com.sistemaconsulta.gestao.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaconsulta.gestao.model.domain.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

}
