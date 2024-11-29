package com.sistemaconsulta.gestao.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.sistemaconsulta.gestao.exceptions.ConsultaSalvarException;
import com.sistemaconsulta.gestao.model.domain.Consulta;

public interface ConsultaService {

	List<Consulta> listar();

	Consulta salvar(Consulta consulta) throws ConsultaSalvarException;

	Consulta agendarConsulta(Consulta consulta);

	Consulta cancelarConsulta(Long consultaId);

	Consulta findById(Long id);

}
