package com.pipiris.tienda.dto.inventario;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class InventarioVariantDTO {
	
	private String talla;
    private String color;
	private String nombreImagen;
	private String codigoBarra;
    private int stock;
    private LocalDateTime ultimaActualizacion;
    private Double precioExtra;

}
