package com.pipiris.tienda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pipiris.tienda.dto.MarcaResponseDTO;
import com.pipiris.tienda.model.Marca;

@Mapper(componentModel = "spring", uses = ProductoMapper.class)
public interface MarcaMapper {

	@Mapping(target = "productos", source = "productos")
	MarcaResponseDTO enDTO(Marca marca);
	
}
