package com.pipiris.tienda.dto.producto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {

	private Long idProducto;
	private String categoria;
	private String modelo;
	private String marca;
	private String publico;
	private String nombreImagen;
	private String codigoBarra;
	private BigDecimal precioUnitario;
	
}
