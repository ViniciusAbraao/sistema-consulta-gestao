package com.sistemaconsulta.gestao.controller;

import java.time.LocalTime;
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

import com.sistemaconsulta.gestao.exceptions.MedicoSalvarException;
import com.sistemaconsulta.gestao.model.domain.Especialidade;
import com.sistemaconsulta.gestao.model.domain.Medico;
import com.sistemaconsulta.gestao.model.repository.MedicoRepository;
import com.sistemaconsulta.gestao.model.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	public Medico salvar(@Valid @RequestBody Medico medico) throws MedicoSalvarException {
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

	@GetMapping("/especialidade/{especialidadeId}")
	public ResponseEntity<List<Medico>> listarPorEspecialidade(@PathVariable Long especialidadeId) {
		List<Medico> medicos = medicoService.listarPorEspecialidade(especialidadeId);
		return ResponseEntity.ok(medicos);
	}

	@GetMapping("/{id}/horarios-disponiveis")
	public ResponseEntity<List<LocalTime>> listarHorariosDisponiveis(@PathVariable Long id) {
		List<LocalTime> horarios = medicoService.listarHorariosDisponiveis(id);
		return ResponseEntity.ok(horarios);
	}

	@PutMapping("/{id}")
	public ResponseEntity editar(@PathVariable Long id, @Valid @RequestBody Medico novoMedico) {
		var med = medicoRepository.findById(id);
		if (!med.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		novoMedico.setId(id);
		Medico medico = medicoRepository.save(novoMedico);
		return ResponseEntity.ok(medico);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		var med = medicoRepository.findById(id);
		if (!med.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		medicoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
