package com.sistemaconsulta.gestao.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaconsulta.gestao.model.domain.Historico;
import com.sistemaconsulta.gestao.model.domain.Paciente;

public interface HistoricoRepository extends JpaRepository<Historico, Long>{

    List<Historico> findByPaciente(Paciente paciente);

}
