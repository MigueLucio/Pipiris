package com.pipiris.tienda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pipiris.tienda.model.Inventario;
import com.pipiris.tienda.model.InventarioId;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, InventarioId>{

	// Buscar todos los inventarios de un producto
	List<Inventario> findByIdProductoId(Long productoId);
	
	// Buscar un inventario específico por combinación única
	Optional<Inventario> findByIdProductoIdAndIdTallaAndIdColor(Long productoId, String talla, String color);

	// Buscar producto + stock bajo
	@Query("SELECT i FROM Inventario i WHERE i.id.productoId = :productoId AND i.stock < :minStock")
	List<Inventario> lowStockByProducto(@Param("productoId") Long productoId, @Param("minStock") int minStock);

	// Buscar por producto y talla
	List<Inventario> findByIdProductoIdAndIdTalla(Long productoId, String talla);
	
	// Buscar por producto y color
	List<Inventario> findByIdProductoIdAndIdColor(Long productoId, String color);
	
}
