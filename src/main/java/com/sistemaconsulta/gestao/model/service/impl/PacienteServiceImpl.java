package com.sistemaconsulta.gestao.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaconsulta.gestao.exceptions.PacienteSalvarException;
import com.sistemaconsulta.gestao.model.domain.Consulta;
import com.sistemaconsulta.gestao.model.domain.Paciente;
import com.sistemaconsulta.gestao.model.repository.ConsultaRepository;
import com.sistemaconsulta.gestao.model.repository.PacienteRepository;
import com.sistemaconsulta.gestao.model.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void atualizar(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void deletar(Paciente paciente) {
        pacienteRepository.delete(paciente);
    }

    @Override
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente salvar(Paciente paciente) throws PacienteSalvarException {
        return pacienteRepository.save(paciente);
    }

	@Override
	public List<Consulta> listarHistorico(Long pacienteId) {
		return consultaRepository.findByPacienteId(pacienteId);
	}


}

