package com.sistemaconsulta.gestao.model.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sistemaconsulta.gestao.model.domain.Consulta;
import com.sistemaconsulta.gestao.model.domain.Medico;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	@Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId AND c.dataConsulta = :dataConsulta")
	Optional<Consulta> findByMedicoAndDataConsulta(Medico medico, LocalDate dataConsulta);
	
	List<Consulta> findByPacienteId(Long pacienteId);

	Optional<Consulta> findByDataConsulta(LocalDate dataConsulta);
	
	Optional<Consulta> findById(Consulta consulta);

}
