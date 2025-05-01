package com.pipiris.tienda.service;

import java.util.List;
import java.util.Optional;

import com.pipiris.tienda.dto.inventario.InventarioRequestDTO;
import com.pipiris.tienda.dto.inventario.InventarioResponseDTO;
import com.pipiris.tienda.dto.inventario.ReabastecerProductoDTO;
import com.pipiris.tienda.model.Inventario;

public interface InventarioService {

	List<InventarioResponseDTO> findInventarioByProductoId(Long productoId);

    InventarioResponseDTO findProductoByVariant(Long productoId, String talla, String color);

    List<InventarioResponseDTO> findProductosLowStock(Long productoId, int minStock);

    List<InventarioResponseDTO> findProductoAndTalla(Long productoId, String talla);

    List<InventarioResponseDTO> findProductoAndColor(Long productoId, String color);

    InventarioResponseDTO crearInventario(InventarioRequestDTO requestDTO);

    List<InventarioResponseDTO> reabastecerInventarioDeProducto(ReabastecerProductoDTO reabastecerDTO);

    void eliminarInventario(Long productoId, String talla, String color);
	
}
