package com.pipiris.tienda.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventario", indexes = 
		{
		@Index(name = "idx_producto_id", columnList = "id_producto"),
	    @Index(name = "idx_talla", columnList = "talla"),
	    @Index(name = "idx_color", columnList = "color")
		})
@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {

	@EmbeddedId
	private InventarioId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productoId")
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
	
	@NotNull @Min(0)
    private int stock;

    private LocalDateTime ultimaActualizacion;

    @DecimalMin("0.0")
    private Double precioExtra;
    
    private String nombreImagen;
    
    @Column(unique = true)
    @NotBlank
    private String codigoBarra;
	
}
