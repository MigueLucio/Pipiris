package com.pipiris.tienda.dto.producto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoRequestDTO {
	
	private String modelo;
	private String publico;
	private String nombreImagen;
	private String codigoBarra;
	private BigDecimal precioUnitario;
	private Long categoria;
	private Long marca;

}
