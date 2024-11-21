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

import com.sistemaconsulta.gestao.exceptions.PacienteSalvarException;
import com.sistemaconsulta.gestao.model.domain.Especialidade;
import com.sistemaconsulta.gestao.model.domain.Historico;
import com.sistemaconsulta.gestao.model.domain.Paciente;
import com.sistemaconsulta.gestao.model.repository.HistoricoRepository;
import com.sistemaconsulta.gestao.model.repository.PacienteRepository;
import com.sistemaconsulta.gestao.model.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private HistoricoRepository historicoRepository;

	@PostMapping
	public Paciente salvar(@Valid @RequestBody Paciente paciente) throws PacienteSalvarException {
		return pacienteService.salvar(paciente);
	}

	@GetMapping
	public List<Paciente> listar() {
		return pacienteService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> buscarPorId(@PathVariable("id") Long id) {
		var paciente = pacienteRepository.findById(id);
		if (!paciente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(paciente.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity editar(@PathVariable Long id, @Valid @RequestBody Paciente novoPaciente) {
		var pac = pacienteRepository.findById(id);
		if (!pac.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		novoPaciente.setId(id);
		Paciente paciente = pacienteRepository.save(novoPaciente);
		return ResponseEntity.ok(paciente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		var pac = pacienteRepository.findById(id);
		if (!pac.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		pacienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{id}/historico")
	public ResponseEntity<Historico> adicionarHistorico(@PathVariable("id") Long id, @RequestBody Historico historico) {
		var paciente = pacienteRepository.findById(id);
		if (!paciente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		historico.setPaciente(paciente.get());
		return ResponseEntity.ok().body(historicoRepository.save(historico));
	}

	@GetMapping("/{id}/historico")
	public ResponseEntity<List<Historico>> buscarHistorico(@PathVariable("id") Long id) {
		var paciente = pacienteRepository.findById(id);
		if (!paciente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		List<Historico> historico = historicoRepository.findByPaciente(paciente.get());
		return ResponseEntity.ok().body(historico);
	}

}
