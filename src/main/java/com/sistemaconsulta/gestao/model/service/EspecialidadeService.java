package com.sistemaconsulta.gestao.model.service;

import java.util.List;

import com.sistemaconsulta.gestao.exceptions.EspecialidadeSalvarException;
import com.sistemaconsulta.gestao.model.domain.Especialidade;

public interface EspecialidadeService {

    Especialidade salvar(Especialidade especialidade) throws EspecialidadeSalvarException;
    void atualizar(Especialidade especialidade);
    void deletar(Especialidade especialidade);
    List<Especialidade> listar();

}
