package com.pipiris.tienda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pipiris.tienda.dto.categoria.CategoriaResponseDTO;
import com.pipiris.tienda.model.Categoria;

@Mapper(componentModel = "spring",uses = ProductoMapper.class)
public interface CategoriaMapper {

	@Mapping(target = "productos", source = "productos")
	CategoriaResponseDTO enDTO(Categoria categoria);
	
}
