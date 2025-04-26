package com.pipiris.tienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pipiris.tienda.dto.CategoriaResponseDTO;
import com.pipiris.tienda.mapper.CategoriaMapper;
import com.pipiris.tienda.model.Categoria;
import com.pipiris.tienda.repository.CategoriaRepository;
import com.pipiris.tienda.service.CategoriaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	private final CategoriaRepository categoriaRepo;
	private final CategoriaMapper categoriaMap;
	
	public CategoriaServiceImpl(CategoriaRepository categoriaRepo, CategoriaMapper categoriaMap) {
		this.categoriaRepo = categoriaRepo;
		this.categoriaMap = categoriaMap;
	}
	
	@Cacheable(value = "categoria")
	@Override
	public List<CategoriaResponseDTO> getAll() {
		
		return categoriaRepo.findAll().stream()
				.map(categoriaMap::enDTO)
				.collect(Collectors.toList());
		
	}

	@Override
	public CategoriaResponseDTO getById(Long idCategoria) {

		Categoria categoria = categoriaRepo.findById(idCategoria)
		.orElseThrow(()-> new EntityNotFoundException("Categoria no encontrada"));
		
		return categoriaMap.enDTO(categoria);
		
	}

	@CacheEvict(value = "categoria", allEntries = true)
	@Override
	public CategoriaResponseDTO create(Categoria categoria) {
		
		Categoria creado = categoriaRepo.save(categoria);
		
		return categoriaMap.enDTO(creado);
		
	}
	
	@CachePut(value = "categoria", key = "#idCategoria")
	@Override
	public CategoriaResponseDTO update(Long idCategoria, Categoria categoria) {
		
		Categoria existente = categoriaRepo.findById(idCategoria)
		.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
		
		existente.setNombre(categoria.getNombre());
		
		Categoria actualizado = categoriaRepo.save(existente);
		
		return categoriaMap.enDTO(actualizado);
		
	}

	@Override
	public void delete(Long idCategoria) {
		
		Categoria existente = categoriaRepo.findById(idCategoria)
		.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
		
		categoriaRepo.delete(existente);
		
	}

	@Override
	public List<CategoriaResponseDTO> getCategoriaByNombre(String nombre) {
		
		return categoriaRepo.findByNombreContainingIgnoreCase(nombre)
				.stream()
				.map(categoriaMap::enDTO)
				.collect(Collectors.toList());
		
	}

}
