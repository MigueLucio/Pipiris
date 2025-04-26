package com.pipiris.tienda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pipiris.tienda.dto.ParaCategoriaDTO;
import com.pipiris.tienda.dto.ParaMarcaDTO;
import com.pipiris.tienda.model.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

	@Mapping(source = "modelo", target = "modelo")
	@Mapping(source = "marca.nombre", target = "marca")
	ParaCategoriaDTO enDTOParaCategoria(Producto producto);
	
	@Mapping(source = "modelo", target = "modelo")
	@Mapping(source = "categoria.nombre", target = "categoria")
	ParaMarcaDTO enDTOParaMarca(Producto producto);
	
}
