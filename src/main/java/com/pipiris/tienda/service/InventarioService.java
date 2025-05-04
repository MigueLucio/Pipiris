package com.pipiris.tienda.service;


import com.pipiris.tienda.dto.inventario.InventarioRequestDTO;
import com.pipiris.tienda.dto.inventario.InventarioResponseDTO;
import com.pipiris.tienda.dto.inventario.InventarioVariantDTO;

import java.util.List;

import com.pipiris.tienda.dto.inventario.InventarioProductoResponseDTO;
import com.pipiris.tienda.dto.inventario.ReabastecerProductoDTO;

public interface InventarioService {

	List<InventarioProductoResponseDTO> findInventarioByProductoId(Long productoId);

    InventarioVariantDTO findProductoByVariant(Long productoId, String talla, String color);

    List<InventarioProductoResponseDTO> findProductosLowStock(Long productoId, int minStock);

    List<InventarioResponseDTO> findProductoAndTalla(Long productoId, String talla);

    List<InventarioResponseDTO> findProductoAndColor(Long productoId, String color);

    InventarioResponseDTO crearInventario(InventarioRequestDTO requestDTO);

    List<InventarioVariantDTO> reabastecerInventarioDeProducto(ReabastecerProductoDTO reabastecerDTO);

    void eliminarInventario(Long productoId, String talla, String color);
	
}
