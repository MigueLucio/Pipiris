package com.pipiris.tienda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pipiris.tienda.dto.inventario.InventarioProductoResponseDTO;
import com.pipiris.tienda.dto.inventario.InventarioVariantDTO;
import com.pipiris.tienda.model.Inventario;
import com.pipiris.tienda.model.Producto;

@Mapper(componentModel = "spring")
public interface InventarioProductoMapper {

	@Mapping(source = "producto.idProducto", target = "productoId")
    @Mapping(source = "producto.categoria.nombre", target = "categoria")
    @Mapping(source = "producto.modelo", target = "modelo")
    @Mapping(source = "producto.marca.nombre", target = "marca")
    @Mapping(source = "inventarios", target = "items")
    InventarioProductoResponseDTO toProductoWithVariantsDTO(
        Producto producto,
        List<Inventario> inventarios
    );
	
	@Mapping(source = "id.talla", target = "talla")
    @Mapping(source = "id.color", target = "color")
    InventarioVariantDTO toVariantDTO(Inventario inventario);
	
	
	List<InventarioVariantDTO> toVariantsDTOs(List<Inventario> inventarios);

}
