package com.sistemaconsulta.gestao.model.service;

import java.util.List;
import java.util.Optional;

import com.sistemaconsulta.gestao.exceptions.ConsultaSalvarException;
import com.sistemaconsulta.gestao.model.domain.Consulta;

public interface ConsultaService {

    Consulta salvar(Consulta consulta) throws ConsultaSalvarException;
    Consulta atualizar(Consulta consulta);
    void deletar(Consulta consulta);
    List<Consulta> listar();
    
    

}
