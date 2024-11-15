package com.sistemaconsulta.gestao.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaconsulta.gestao.exceptions.PacienteSalvarException;
import com.sistemaconsulta.gestao.model.domain.Paciente;
import com.sistemaconsulta.gestao.model.repository.PacienteRepository;
import com.sistemaconsulta.gestao.model.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

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


}

