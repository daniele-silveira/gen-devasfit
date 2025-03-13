package com.generation.devasfit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.devasfit.model.Treinos;
import com.generation.devasfit.repository.CategoriaRepository;
import com.generation.devasfit.repository.TreinosRepository;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/treinos")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class TreinosController {

	@Autowired
	private TreinosRepository treinosRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity <List<Treinos>> getAll() {
		return ResponseEntity.ok(treinosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Treinos> getByid(@PathVariable Long id) {
		return treinosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nometreino/{nomeTreino}")
	public ResponseEntity<List<Treinos>> getByTitulo(@PathVariable String nomeTreino){
		return ResponseEntity.ok(treinosRepository.
				findAllByNomeTreinoContainingIgnoreCase(nomeTreino));
	}
	
	@PostMapping
	public ResponseEntity<Treinos> post(@Valid @RequestBody Treinos treinos){
		if (categoriaRepository.existsById(treinos.getCategoria().getId()))
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(treinosRepository.save(treinos));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Treinos> put(@Valid @RequestBody Treinos treinos) {
		if (treinosRepository.existsById(treinos.getId())) {
			
		if (categoriaRepository.existsById(treinos.getCategoria().getId()))
		return ResponseEntity.status(HttpStatus.OK)
						.body(treinosRepository.save(treinos));
						
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!, null");
				
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Treinos> treinos = treinosRepository.findById(id);
		
		if(treinos.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		treinosRepository.deleteById(id);
		
	}
	
}
