package com.pipiris.tienda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pipiris.tienda.dto.inventario.InventarioRequestDTO;
import com.pipiris.tienda.dto.inventario.InventarioResponseDTO;
import com.pipiris.tienda.model.Inventario;

@Mapper(componentModel = "spring")
public interface InventarioMapper {
	
	
	@Mapping(target = "id", expression = "java(new InventarioId(requestDTO.getProductoId(), requestDTO.getTalla(), requestDTO.getColor()))")
    @Mapping(target = "producto", ignore = true) // Se debe setear desde el service
    @Mapping(target = "ultimaActualizacion", expression = "java(java.time.LocalDateTime.now())")
	Inventario enModelo(InventarioRequestDTO requestDTO);
	
	// Indicamos explicitamente estos tres campos
    @Mapping(source = "id.productoId", target = "productoId")
    @Mapping(source = "id.talla",     target = "talla")
    @Mapping(source = "id.color",     target = "color")
	InventarioResponseDTO enDTO(Inventario inventario);

}
