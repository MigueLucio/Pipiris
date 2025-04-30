package com.pipiris.tienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pipiris.tienda.dto.marca.MarcaResponseDTO;
import com.pipiris.tienda.mapper.MarcaMapper;
import com.pipiris.tienda.model.Marca;
import com.pipiris.tienda.repository.MarcaRepository;
import com.pipiris.tienda.service.MarcaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MarcaServiceImpl implements MarcaService{

	private final MarcaRepository marcaRepo;
	private final MarcaMapper marcaMap;
	
	public MarcaServiceImpl(MarcaRepository marcaRepo, MarcaMapper marcaMap) {
		this.marcaRepo = marcaRepo;
		this.marcaMap = marcaMap;
	}

	@Cacheable(value = "marca")
	@Override
	public List<MarcaResponseDTO> getAll() {
		
		return marcaRepo.findAll().stream()
				.map(marcaMap::enDTO)
				.collect(Collectors.toList());
		
	}

	@Cacheable(value = "marca")
	@Override
	public MarcaResponseDTO getById(Long idMarca) {
		
		Marca marca = marcaRepo.findById(idMarca)
		.orElseThrow(() -> new EntityNotFoundException("Marca no encontrada"));
		
		return marcaMap.enDTO(marca);
		
	}

	@CacheEvict(value = "marca", allEntries = true)
	@Override
	public MarcaResponseDTO create(Marca marca) {
		
		Marca creado = marcaRepo.save(marca);
		
		return marcaMap.enDTO(creado);
		
	}

	@CachePut(value = "marca", key = "#idMarca")
	@Override
	public MarcaResponseDTO update(Long idMarca, Marca marca) {
		
		Marca existente = marcaRepo.findById(idMarca)
		.orElseThrow(() -> new EntityNotFoundException("Marca no encontrada"));
		
		existente.setNombre(marca.getNombre());
		existente.setContacto(marca.getContacto());
		existente.setDireccion(marca.getDireccion());
		
		Marca actualizado = marcaRepo.save(existente);
		
		return marcaMap.enDTO(actualizado);
		
	}

	@Override
	public void delete(Long idMarca) {
		
		Marca existente = marcaRepo.findById(idMarca)
		.orElseThrow(() -> new EntityNotFoundException("Marca no encontrada"));
		
		marcaRepo.delete(existente);
		
	}

	@Override
	public List<MarcaResponseDTO> getMarcaByNombre(String nombre) {
		
		return marcaRepo.findByNombreContainingIgnoreCase(nombre)
				.stream()
				.map(marcaMap::enDTO)
				.collect(Collectors.toList());
		
	}

}
