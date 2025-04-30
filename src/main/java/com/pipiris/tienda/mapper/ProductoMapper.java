package com.pipiris.tienda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pipiris.tienda.dto.categoria.ParaCategoriaDTO;
import com.pipiris.tienda.dto.marca.ParaMarcaDTO;
import com.pipiris.tienda.dto.producto.ProductoRequestDTO;
import com.pipiris.tienda.dto.producto.ProductoResponseDTO;
import com.pipiris.tienda.model.Producto;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class,MarcaMapper.class})
public interface ProductoMapper {
	
	@Mapping(target = "categoria", ignore = true)
	@Mapping(target = "marca", ignore = true)
	Producto enModelo(ProductoRequestDTO requestDTO);
	
	@Mapping(source = "categoria.nombre", target = "categoria")
	@Mapping(source = "marca.nombre", target = "marca")
	ProductoResponseDTO enDTO(Producto producto);

	@Mapping(source = "modelo", target = "modelo")
	@Mapping(source = "marca.nombre", target = "marca")
	ParaCategoriaDTO enDTOParaCategoria(Producto producto);
	
	@Mapping(source = "modelo", target = "modelo")
	@Mapping(source = "categoria.nombre", target = "categoria")
	ParaMarcaDTO enDTOParaMarca(Producto producto);
	
}
