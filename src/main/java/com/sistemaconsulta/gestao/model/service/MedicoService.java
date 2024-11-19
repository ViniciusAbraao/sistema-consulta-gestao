package com.sistemaconsulta.gestao.model.service;


import java.time.LocalTime;
import java.util.List;

import com.sistemaconsulta.gestao.exceptions.MedicoSalvarException;
import com.sistemaconsulta.gestao.model.domain.Medico;

public interface MedicoService {

    Medico salvar(Medico medico) throws MedicoSalvarException;
    void atualizar(Medico medico);
    void deletar(Medico medico);
    List<Medico> listar();
    List<Medico> listarPorEspecialidade(Long especialidadeId);
    List<LocalTime> listarHorariosDisponiveis(Long medicoId);

}
