package com.pipiris.tienda.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "marca")
@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Marca implements Serializable{

private final static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMarca;
	
	@NotBlank
	@Column(unique = true)
	private String nombre;
	
	@NotBlank
	@Pattern(regexp = "^[0-9]{9}$", message = "ingresar numero de telefono valido")
	private String contacto;
	
	@NotBlank
	private String direccion;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "marca"
			)
	private List<Producto> productos;
	
}
