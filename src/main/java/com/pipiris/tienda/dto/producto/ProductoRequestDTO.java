package com.pipiris.tienda.dto.producto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoRequestDTO {

	private Long categoria;
	private String modelo;
	private Long marca;
	private String publico;
	private BigDecimal precioUnitario;

}
