package com.sistemaconsulta.gestao.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaconsulta.gestao.exceptions.ConsultaSalvarException;
import com.sistemaconsulta.gestao.model.domain.Consulta;
import com.sistemaconsulta.gestao.model.domain.StatusConsulta;
import com.sistemaconsulta.gestao.model.repository.ConsultaRepository;
import com.sistemaconsulta.gestao.model.service.ConsultaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    public Consulta salvar(@Valid @RequestBody Consulta consulta)
            throws ConsultaSalvarException {
        return consultaService.salvar(consulta);
    }

    @GetMapping
    public List<Consulta> listar() {
        return consultaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable("id") Long id) {
        var consulta = consultaRepository.findById(id);
        if (!consulta.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(consulta.get());
    }

    @PutMapping
    public Consulta atualizar(@Valid @RequestBody Consulta consulta){
        return consultaService.atualizar(consulta);
    }
    
    @PostMapping("/agendar")
    public ResponseEntity<Consulta> agendarConsulta(@RequestBody Consulta consulta){
    	var consultaAgendada = consultaService.agendarConsulta(consulta);
    	return ResponseEntity.ok(consultaAgendada);
    }
    
    @PutMapping("/{consultaId}/cancelar")
    public ResponseEntity<Consulta> cancelarConsulta(@PathVariable Long consultaId) {
        Consulta consultaCancelada = consultaService.cancelarConsulta(consultaId);
        return ResponseEntity.ok(consultaCancelada);
    }
    

    
}
