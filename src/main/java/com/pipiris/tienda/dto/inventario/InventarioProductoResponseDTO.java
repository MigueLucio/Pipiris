package com.pipiris.tienda.dto.inventario;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class InventarioProductoResponseDTO {

	 private Long productoId;
	 private String categoria;
	 private String modelo;
	 private String marca;

	 // Todas las variantes de inventario para este producto
	 private List<InventarioVariantDTO> items;
	    
}
