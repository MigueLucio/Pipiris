package com.pipiris.tienda.model;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventario")
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
    
    public static Inventario of(Producto producto, String talla, String color, int stock) {
        Inventario inv = new Inventario();
        inv.id = new InventarioId(producto.getIdProducto(), talla, color);
        inv.producto = producto;
        inv.stock = stock;
        inv.ultimaActualizacion = LocalDateTime.now();
        return inv;
    }
    
    public void actualizarStock(int nuevoStock) {
        this.stock = nuevoStock;
        this.ultimaActualizacion = LocalDateTime.now();
    }
	
}
