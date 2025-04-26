package com.pipiris.tienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pipiris.tienda.dto.MarcaResponseDTO;
import com.pipiris.tienda.model.Categoria;
import com.pipiris.tienda.model.Marca;
import com.pipiris.tienda.service.MarcaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/marcas")
public class MarcaController {

	private final MarcaService marcaService;

	public MarcaController(MarcaService marcaService) {
		this.marcaService = marcaService;
	}
	
	@GetMapping
	public ResponseEntity<List<MarcaResponseDTO>> obtenerMarca(){
		
		return ResponseEntity.ok(marcaService.getAll());
		
	}
	
	@GetMapping("/{idMarca}")
	public ResponseEntity<MarcaResponseDTO> obtenerById(@PathVariable Long idMarca) {
		
		return ResponseEntity.ok(marcaService.getById(idMarca));
		
	}
		
	@PostMapping
	public ResponseEntity<MarcaResponseDTO> crear(@RequestBody @Valid Marca marca){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(marcaService.create(marca));
		
	}
	
	@PutMapping("/{idMarca}")
	public ResponseEntity<MarcaResponseDTO> editar(@PathVariable Long idMarca, @RequestBody @Valid Marca marca){
		
		return ResponseEntity.ok(marcaService.update(idMarca, marca));
		
	}
	
	@DeleteMapping("/{idMarca}")
	public ResponseEntity<Categoria> eliminar(@PathVariable Long idMarca){
		
		marcaService.delete(idMarca);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<MarcaResponseDTO>> buscarMarcaByNombre(@RequestParam String nombre){
		
		List<MarcaResponseDTO> marcas = marcaService.getMarcaByNombre(nombre);
		return ResponseEntity.ok(marcas);
		
	}
	
	
}
