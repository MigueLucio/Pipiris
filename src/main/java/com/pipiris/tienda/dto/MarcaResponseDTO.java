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
public class MarcaResponseDTO {

	private Long idMarca;
	private String nombre;
	private String contacto;
	private String direccion;
	private List<ParaCategoriaDTO> productos;
	
}
