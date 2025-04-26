package com.pipiris.tienda.dto;

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
public class CategoriaResponseDTO {
	
	private Long idCategoria;
	private String nombre;
	private List<ParaCategoriaDTO> productos;

}