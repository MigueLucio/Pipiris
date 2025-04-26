package com.pipiris.tienda.service;

import java.util.List;

import com.pipiris.tienda.dto.CategoriaResponseDTO;
import com.pipiris.tienda.model.Categoria;

public interface CategoriaService {

	List<CategoriaResponseDTO> getAll();
	CategoriaResponseDTO getById(Long idCategoria);
	CategoriaResponseDTO create(Categoria categoria);
	CategoriaResponseDTO update(Long idCategoria, Categoria categoria);
	void delete(Long idCategoria);
	List<CategoriaResponseDTO> getCategoriaByNombre(String nombre);
	
}
