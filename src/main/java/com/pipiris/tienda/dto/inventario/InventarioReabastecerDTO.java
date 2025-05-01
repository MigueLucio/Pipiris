package com.pipiris.tienda.dto.inventario;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class InventarioReabastecerDTO {

	 @NotBlank
	 private String color;

	 @NotBlank   
	 private String talla;

	 @Min(1)
	 private int cantidad;
}
