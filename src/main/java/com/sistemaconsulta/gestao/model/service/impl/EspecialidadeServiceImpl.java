package com.sistemaconsulta.gestao.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaconsulta.gestao.exceptions.EspecialidadeSalvarException;
import com.sistemaconsulta.gestao.model.domain.Especialidade;
import com.sistemaconsulta.gestao.model.repository.EspecialidadeRepository;
import com.sistemaconsulta.gestao.model.service.EspecialidadeService;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService{

    @Autowired
    private EspecialidadeRepository especialidadeRepository;


    @Override
    public void atualizar(Especialidade especialidade) {
        especialidadeRepository.save(especialidade);
    }

    @Override
    public void deletar(Especialidade especialidade) {
        especialidadeRepository.delete(especialidade);
    }

    @Override
    public List<Especialidade> listar() {
        return especialidadeRepository.findAll();
    }

    @Override
    public Especialidade salvar(Especialidade especialidade) throws EspecialidadeSalvarException {
        return especialidadeRepository.save(especialidade);
    }


}
