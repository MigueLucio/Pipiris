package com.pipiris.tienda.dto.marca;

import java.util.List;

import com.pipiris.tienda.dto.categoria.ParaCategoriaDTO;

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
