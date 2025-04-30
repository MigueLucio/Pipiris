package com.pipiris.tienda.service;

import java.util.List;

import com.pipiris.tienda.dto.producto.ProductoRequestDTO;
import com.pipiris.tienda.dto.producto.ProductoResponseDTO;

public interface ProductoService {

	List<ProductoResponseDTO> getAll();
	ProductoResponseDTO getById(Long idProducto);
	ProductoResponseDTO create(ProductoRequestDTO requestDTO);
	ProductoResponseDTO update(Long idProducto, ProductoRequestDTO requestDTO);
	void delete(Long idProducto);
	List<ProductoResponseDTO> getProductoByModelo(String modelo);
	
}
