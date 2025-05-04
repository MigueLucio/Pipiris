package com.pipiris.tienda.dto.inventario;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class InventarioRequestDTO {
	
	@NotNull
	private Long productoId;
	
	@NotBlank
	private String talla;
	
	@NotBlank
	private String color;
	
	private String nombreImagen;
	
	private String codigoBarra;
	
	@Min(0)
	private int stock;
	
	private Double precioExtra;

}
