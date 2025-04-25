package com.pipiris.tienda.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class InventarioId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "id_producto", nullable = false)
	private Long productoId;
	
	@NotBlank
	@Column(name = "talla", length = 20, nullable = false)
	private String talla;
	
	@NotBlank
	@Column(name = "color", length = 20, nullable = false)
	private String color;

	public InventarioId() {
	}

	public InventarioId(@NotNull Long productoId, @NotBlank String talla, @NotBlank String color) {
		this.productoId = productoId;
		this.talla = talla;
		this.color = color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, productoId, talla);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventarioId other = (InventarioId) obj;
		return Objects.equals(color, other.color) && Objects.equals(productoId, other.productoId)
				&& Objects.equals(talla, other.talla);
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
