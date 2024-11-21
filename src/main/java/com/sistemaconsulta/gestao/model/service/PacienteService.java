package com.sistemaconsulta.gestao.model.service;

import java.util.List;

import com.sistemaconsulta.gestao.exceptions.PacienteSalvarException;
import com.sistemaconsulta.gestao.model.domain.Paciente;

public interface PacienteService {

    Paciente salvar(Paciente paciente) throws PacienteSalvarException;
    List<Paciente> listar();
    

}
