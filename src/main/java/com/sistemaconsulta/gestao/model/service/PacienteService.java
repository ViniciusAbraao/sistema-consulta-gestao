package com.sistemaconsulta.gestao.model.service;

import java.util.List;

import com.sistemaconsulta.gestao.exceptions.PacienteSalvarException;
import com.sistemaconsulta.gestao.model.domain.Consulta;
import com.sistemaconsulta.gestao.model.domain.Paciente;

public interface PacienteService {

    Paciente salvar(Paciente paciente) throws PacienteSalvarException;
    void atualizar(Paciente paciente);
    void deletar(Paciente paciente);
    List<Paciente> listar();
    List<Consulta> listarHistorico(Long pacienteId);

}
