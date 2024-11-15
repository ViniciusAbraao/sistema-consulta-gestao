package com.sistemaconsulta.gestao.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaconsulta.gestao.model.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
}
