package com.pipiris.tienda.service;

import java.util.List;

import com.pipiris.tienda.dto.MarcaResponseDTO;
import com.pipiris.tienda.model.Marca;

public interface MarcaService {

	List<MarcaResponseDTO> getAll();
	MarcaResponseDTO getById(Long idMarca);
	MarcaResponseDTO create(Marca marca);
	MarcaResponseDTO update(Long idMarca, Marca marca);
	void delete(Long idMarca);
	List<MarcaResponseDTO> getMarcaByNombre(String nombre);
	
}
