package com.sistemaconsulta.gestao.model.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemaconsulta.gestao.exceptions.MedicoSalvarException;
import com.sistemaconsulta.gestao.model.domain.Medico;
import com.sistemaconsulta.gestao.model.repository.ConsultaRepository;
import com.sistemaconsulta.gestao.model.repository.MedicoRepository;
import com.sistemaconsulta.gestao.model.service.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private ConsultaRepository consultaRepository;

	@Override
	public void atualizar(Medico medico) {
		medicoRepository.save(medico);
	}

	@Override
	public void deletar(Medico medico) {
		medicoRepository.delete(medico);
	}

	@Override
	public List<Medico> listar() {
		return medicoRepository.findAll();
	}

	@Override
	public Medico salvar(Medico medico) throws MedicoSalvarException {
		return medicoRepository.save(medico);
	}

	@Override
	public List<Medico> listarPorEspecialidade(Long especialidadeId) {
		return medicoRepository.findByEspecialidadeId(especialidadeId);
	}

	@Override
	public List<LocalDateTime> listarHorariosDisponiveis(Long medicoId) {
		var medico = medicoRepository.findById(medicoId)
				.orElseThrow(() -> new RuntimeException("Médico não encontrado"));

		List<LocalDateTime> horariosLivres = new ArrayList(medico.getHorariosDisponiveis());

		return horariosLivres;
	}

}
