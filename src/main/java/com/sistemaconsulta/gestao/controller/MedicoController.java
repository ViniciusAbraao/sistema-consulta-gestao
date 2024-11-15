package com.sistemaconsulta.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaconsulta.gestao.exceptions.MedicoSalvarException;
import com.sistemaconsulta.gestao.model.domain.Medico;
import com.sistemaconsulta.gestao.model.repository.MedicoRepository;
import com.sistemaconsulta.gestao.model.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public Medico salvar(@Valid @RequestBody Medico medico)
            throws MedicoSalvarException {
        return medicoService.salvar(medico);
    }

    @GetMapping
    public List<Medico> listar() {
        return medicoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable("id") Long id) {
        var medico = medicoRepository.findById(id);
        if (!medico.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(medico.get());
    }
}

