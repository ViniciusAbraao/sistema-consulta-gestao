package com.sistemaconsulta.gestao.model.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaconsulta.gestao.exceptions.ConsultaSalvarException;
import com.sistemaconsulta.gestao.model.domain.Consulta;
import com.sistemaconsulta.gestao.model.domain.Medico;
import com.sistemaconsulta.gestao.model.domain.Paciente;
import com.sistemaconsulta.gestao.model.domain.StatusConsulta;
import com.sistemaconsulta.gestao.model.repository.ConsultaRepository;
import com.sistemaconsulta.gestao.model.repository.MedicoRepository;
import com.sistemaconsulta.gestao.model.repository.PacienteRepository;
import com.sistemaconsulta.gestao.model.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public List<Consulta> listar() {
		return consultaRepository.findAll();
	}

	@Override
	public Consulta salvar(Consulta consulta) throws ConsultaSalvarException {
		return consultaRepository.save(consulta);
	}

	@Override
	public Consulta agendarConsulta(Consulta consulta) {

		Medico medico = medicoRepository.findById(consulta.getMedico().getId())
				.orElseThrow(() -> new RuntimeException("Médico não encontrado"));

		Paciente paciente = pacienteRepository.findById(consulta.getPaciente().getId())
				.orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

		Optional<Consulta> dataConsulta = consultaRepository.findByDataConsulta(consulta.getDataConsulta());

		Optional<Consulta> consultaExistente = consultaRepository.findByMedicoAndDataConsulta(medico,
				consulta.getDataConsulta());

		if (consultaExistente.isPresent()) {
			throw new RuntimeException("Horário indisponível para este médico");
		}

		consulta.setStatus(StatusConsulta.AGENDADA);
		return consultaRepository.save(consulta);
	}

	@Override
	public Consulta cancelarConsulta(Long consultaId) {
		Consulta consulta = consultaRepository.findById(consultaId)
				.orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

		if (StatusConsulta.CANCELADA.equals(consulta.getStatus())) {
			throw new RuntimeException("Consulta já foi cancelada");
		}

		consulta.setStatus(StatusConsulta.CANCELADA);
		return consultaRepository.save(consulta);
	}

	public Consulta findById(Long id) {
	    return consultaRepository.findById(id).orElse(null);
	}



}
