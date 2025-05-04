package com.pipiris.tienda.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pipiris.tienda.dto.inventario.InventarioRequestDTO;
import com.pipiris.tienda.dto.inventario.InventarioResponseDTO;
import com.pipiris.tienda.dto.inventario.InventarioVariantDTO;
import com.pipiris.tienda.dto.inventario.InventarioProductoResponseDTO;
import com.pipiris.tienda.dto.inventario.ReabastecerProductoDTO;
import com.pipiris.tienda.mapper.InventarioMapper;
import com.pipiris.tienda.mapper.InventarioProductoMapper;
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
	private final InventarioProductoMapper inventarioProMap;
	private final InventarioMapper inventarioMap;
	private final ProductoRepository productoRepo;
	
	

	public InventarioServiceImpl(InventarioRepository inventarioRepo, InventarioProductoMapper inventarioProMap,
			InventarioMapper inventarioMap, ProductoRepository productoRepo) {
		super();
		this.inventarioRepo = inventarioRepo;
		this.inventarioProMap = inventarioProMap;
		this.inventarioMap = inventarioMap;
		this.productoRepo = productoRepo;
	}

	@Override
	public List<InventarioProductoResponseDTO> findInventarioByProductoId(Long productoId) {
		
		Producto prod = productoRepo.findById(productoId)
		         .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		 
		List<Inventario> invs = inventarioRepo.findByIdProductoId(productoId);
		
		if (invs.isEmpty()) {
            throw new EntityNotFoundException("No hay inventario para el producto con id " + productoId);
        }
        
        return List.of(inventarioProMap.toProductoWithVariantsDTO(prod, invs));
        
	}

	@Override
	public InventarioVariantDTO findProductoByVariant(Long productoId, String talla, String color) {
		
		Inventario inv = inventarioRepo.findByIdProductoIdAndIdTallaAndIdColor(productoId, talla, color)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Inventario no encontrado para producto = " + productoId +
                    ", talla = " + talla +
                    ", color = " + color));
		
        return inventarioProMap.toVariantDTO(inv);
        
	}

	@Override
	public List<InventarioProductoResponseDTO> findProductosLowStock(Long productoId, int minStock) {

		Producto prod = productoRepo.findById(productoId)
	            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		List<Inventario> low = inventarioRepo.lowStockByProducto(productoId, minStock);
		
		if (low.isEmpty()) {
            throw new EntityNotFoundException("No hay inventarios con stock < " + minStock +
                                              " para producto " + productoId);
        }
        
        return List.of(inventarioProMap.toProductoWithVariantsDTO(prod, low));
        
	}

	@Override
	public List<InventarioResponseDTO> findProductoAndTalla(Long productoId, String talla) {
		
		List<Inventario> invs = inventarioRepo.findByIdProductoIdAndIdTalla(productoId, talla);
		
		if (invs.isEmpty()) {
            throw new EntityNotFoundException("No hay inventarios con la " + talla +
                                              " para producto " + productoId);
        }
        
        return invs.stream().map(inventarioMap::enDTO).collect(Collectors.toList());
        
	}

	@Override
	public List<InventarioResponseDTO> findProductoAndColor(Long productoId, String color) {

		List<Inventario> invs = inventarioRepo.findByIdProductoIdAndIdColor(productoId, color);
        
		if (invs.isEmpty()) {
            throw new EntityNotFoundException("No hay inventarios para producto = " + productoId +
                                              ", color = " + color);
        }
        
        return invs.stream().map(inventarioMap::enDTO).collect(Collectors.toList());
        
	}

	@Override
	public InventarioResponseDTO crearInventario(InventarioRequestDTO requestDTO) {

		Inventario inv = inventarioMap.enModelo(requestDTO);
		
        Producto prod = productoRepo.findById(requestDTO.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        
        inv.setProducto(prod);
        inventarioRepo.save(inv);
        
        return inventarioMap.enDTO(inv);
        
	}

	@Override
	public List<InventarioVariantDTO> reabastecerInventarioDeProducto(ReabastecerProductoDTO reabastecerDTO) {

		Long productoId = reabastecerDTO.getProductoId();

	    List<Inventario> actualizados = reabastecerDTO.getItems().stream()
	        .map(item -> {
	            Inventario inv = inventarioRepo
	                .findByIdProductoIdAndIdTallaAndIdColor(productoId, item.getTalla(), item.getColor())
	                .orElseThrow(() -> new EntityNotFoundException(
	                    "No se encontró inventario talla = " + item.getTalla() +
	                    ", color = " + item.getColor()
	                ));
	            
	            inv.setStock(inv.getStock() + item.getCantidad());
	            inv.setUltimaActualizacion(LocalDateTime.now());
	            
	            return inventarioRepo.save(inv); // devolvemos el guardado
	        })
	        .toList();

	    return inventarioProMap.toVariantsDTOs(actualizados);

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
