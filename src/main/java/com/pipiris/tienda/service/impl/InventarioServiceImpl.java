package com.pipiris.tienda.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pipiris.tienda.dto.inventario.InventarioRequestDTO;
import com.pipiris.tienda.dto.inventario.InventarioResponseDTO;
import com.pipiris.tienda.dto.inventario.ReabastecerProductoDTO;
import com.pipiris.tienda.mapper.InventarioMapper;
import com.pipiris.tienda.model.Inventario;
import com.pipiris.tienda.model.Producto;
import com.pipiris.tienda.repository.InventarioRepository;
import com.pipiris.tienda.repository.ProductoRepository;
import com.pipiris.tienda.service.InventarioService;
import com.pipiris.tienda.model.InventarioId;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioServiceImpl implements InventarioService{
	
	private final InventarioRepository inventarioRepo;
	private final InventarioMapper inventarioMap;
	private final ProductoRepository productoRepo;
	
	public InventarioServiceImpl(InventarioRepository inventarioRepo, InventarioMapper inventarioMap,
			ProductoRepository productoRepo) {
		this.inventarioRepo = inventarioRepo;
		this.inventarioMap = inventarioMap;
		this.productoRepo = productoRepo;
	}

	@Override
	public List<InventarioResponseDTO> findInventarioByProductoId(Long productoId) {
		
		List<Inventario> inventarios = inventarioRepo.findByIdProductoId(productoId);
        
		if (inventarios.isEmpty()) {
            throw new EntityNotFoundException("No hay inventario para el producto con id " + productoId);
        }
        
        return inventarios.stream()
                .map(inventarioMap::enDTO)
                .toList();
        
	}

	@Override
	public InventarioResponseDTO findProductoByVariant(Long productoId, String talla, String color) {
		
		Inventario inv = inventarioRepo.findByIdProductoIdAndIdTallaAndIdColor(productoId, talla, color)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Inventario no encontrado para producto = " + productoId +
                    ", talla = " + talla +
                    ", color = " + color));
		
        return inventarioMap.enDTO(inv);
        
	}

	@Override
	public List<InventarioResponseDTO> findProductosLowStock(Long productoId, int minStock) {

		List<Inventario> lista = inventarioRepo.lowStockByProducto(productoId, minStock);
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay inventarios con stock < " + minStock +
                                              " para producto " + productoId);
        }
        
        return lista.stream()
                .map(inventarioMap::enDTO)
                .toList();
        
	}

	@Override
	public List<InventarioResponseDTO> findProductoAndTalla(Long productoId, String talla) {
		
		List<Inventario> lista = inventarioRepo.findByIdProductoIdAndIdTalla(productoId, talla);
		
		
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay inventarios para producto = " + productoId +
                                              ", talla = " + talla);
        }
        
        return lista.stream()
                .map(inventarioMap::enDTO)
                .toList();
        
	}

	@Override
	public List<InventarioResponseDTO> findProductoAndColor(Long productoId, String color) {

		List<Inventario> lista = inventarioRepo.findByIdProductoIdAndIdColor(productoId, color);
        
		if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay inventarios para producto = " + productoId +
                                              ", color = " + color);
        }
        
        return lista.stream()
                .map(inventarioMap::enDTO)
                .toList();
        
	}

	@Override
	public InventarioResponseDTO crearInventario(InventarioRequestDTO requestDTO) {

		Inventario inv = inventarioMap.enModelo(requestDTO);
		
        Producto prod = productoRepo.findById(requestDTO.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        
        inv.setProducto(prod);
        
        Inventario guardado = inventarioRepo.save(inv);
        
        return inventarioMap.enDTO(guardado);
        
	}

	@Override
	public List<InventarioResponseDTO> reabastecerInventarioDeProducto(ReabastecerProductoDTO reabastecerDTO) {

		Long productoId = reabastecerDTO.getProductoId();
        
		return reabastecerDTO.getItems().stream()
            .map(item -> {
            	
                Inventario inv = inventarioRepo.findByIdProductoIdAndIdTallaAndIdColor(
                        productoId, item.getTalla(), item.getColor())
                    .orElseThrow(() -> new EntityNotFoundException(
                        "No se encontró inventario talla = " + item.getTalla() +
                        ", color = " + item.getColor()));
                
                inv.setStock(inv.getStock() + item.getCantidad());
                inv.setUltimaActualizacion(LocalDateTime.now());
                
                return inventarioMap.enDTO(inventarioRepo.save(inv));
                
            })
            .toList();

	}

	@Override
	public void eliminarInventario(Long productoId, String talla, String color) {
		
		InventarioId id = new InventarioId(productoId, talla, color);
		
        if (!inventarioRepo.existsById(id)) {
            throw new EntityNotFoundException("No existe inventario para eliminar");
        }
        
        inventarioRepo.deleteById(id);
        
	}
	
}
