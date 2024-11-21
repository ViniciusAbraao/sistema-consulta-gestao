package com.sistemaconsulta.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaconsulta.gestao.exceptions.EspecialidadeSalvarException;
import com.sistemaconsulta.gestao.model.domain.Especialidade;
import com.sistemaconsulta.gestao.model.repository.EspecialidadeRepository;
import com.sistemaconsulta.gestao.model.service.EspecialidadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @PostMapping
    public Especialidade salvar(@Valid @RequestBody Especialidade especialidade)
            throws EspecialidadeSalvarException {
        return especialidadeService.salvar(especialidade);
    }

    @GetMapping
    public List<Especialidade> listar() {
        return especialidadeService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscarPorId(@PathVariable("id") Long id) {
        var especialidade = especialidadeRepository.findById(id);
        if (!especialidade.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(especialidade.get());
    }
    
   @PutMapping("/{id}")
   public ResponseEntity editar(@PathVariable Long id, @Valid @RequestBody Especialidade novaEspecialidade) {
	   var espec = especialidadeRepository.findById(id);
	   if(!espec.isPresent()) {
		   return ResponseEntity.notFound().build();
	   }
	   novaEspecialidade.setId(id);
	   Especialidade especialidade = especialidadeRepository.save(novaEspecialidade);
	   return ResponseEntity.ok(especialidade);
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> excluir(@PathVariable Long id){
	   var espec = especialidadeRepository.findById(id);
	   if(!espec.isPresent()) {
		   return ResponseEntity.notFound().build();
	   }
	   especialidadeRepository.deleteById(id);
	   return ResponseEntity.noContent().build();
   }
   
    
    
    
}

