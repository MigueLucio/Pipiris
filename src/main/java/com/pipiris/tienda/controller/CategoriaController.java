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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pipiris.tienda.dto.CategoriaResponseDTO;
import com.pipiris.tienda.model.Categoria;
import com.pipiris.tienda.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private final CategoriaService categoriaService;
	
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaResponseDTO>> obtenerCategorias(){
		
		return  ResponseEntity.ok(categoriaService.getAll());
		
	}
	
	@GetMapping("/{idCategoria}")
	public ResponseEntity<CategoriaResponseDTO> obtenerById(@PathVariable Long idCategoria){

		return ResponseEntity.ok(categoriaService.getById(idCategoria));
		
	}

	@PostMapping
	public ResponseEntity<CategoriaResponseDTO> crear(@RequestBody @Valid Categoria categoria){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.create(categoria)); 
		
	}
	
	@PutMapping("/{idCategoria}")
	public ResponseEntity<CategoriaResponseDTO> editar(@PathVariable Long idCategoria, @RequestBody @Valid Categoria categoria){
		
		return ResponseEntity.ok(categoriaService.update(idCategoria, categoria));
		
	}
	
	@DeleteMapping("/{idCategoria}")
	public ResponseEntity<Categoria> eliminar(@PathVariable Long idCategoria){
		
		categoriaService.delete(idCategoria);
		return ResponseEntity.noContent().build();
	
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<CategoriaResponseDTO>> buscarCategoriaByNombre(@RequestParam String nombre){
		
		List<CategoriaResponseDTO> categorias = categoriaService.getCategoriaByNombre(nombre);
		return ResponseEntity.ok(categorias);
		
	}
	
}
