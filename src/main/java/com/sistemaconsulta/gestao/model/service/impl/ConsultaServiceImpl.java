package com.sistemaconsulta.gestao.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaconsulta.gestao.exceptions.ConsultaSalvarException;
import com.sistemaconsulta.gestao.model.domain.Consulta;
import com.sistemaconsulta.gestao.model.repository.ConsultaRepository;
import com.sistemaconsulta.gestao.model.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void atualizar(Consulta consulta) {
        consultaRepository.save(consulta);
    }

    @Override
    public void deletar(Consulta consulta) {
        consultaRepository.delete(consulta);
    }

    @Override
    public List<Consulta> listar() {
        return consultaRepository.findAll();
    }

    @Override
    public Consulta salvar(Consulta consulta) throws ConsultaSalvarException {
        return consultaRepository.save(consulta);
    }



}
