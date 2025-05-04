package com.pipiris.tienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pipiris.tienda.dto.inventario.InventarioRequestDTO;
import com.pipiris.tienda.dto.inventario.InventarioResponseDTO;
import com.pipiris.tienda.dto.inventario.InventarioVariantDTO;
import com.pipiris.tienda.dto.inventario.InventarioProductoResponseDTO;
import com.pipiris.tienda.dto.inventario.ReabastecerProductoDTO;
import com.pipiris.tienda.service.InventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {

	private final InventarioService inventarioService;

	public InventarioController(InventarioService inventarioService) {
		this.inventarioService = inventarioService;
	}
	
	@GetMapping("/producto/{productoId}")
	public ResponseEntity<List<InventarioProductoResponseDTO>> listarInventarioPorProductoId(@PathVariable Long productoId){
		
		return ResponseEntity.ok(inventarioService.findInventarioByProductoId(productoId));
	
	}
	
	@GetMapping("/{productoId}/variante")
	public ResponseEntity<InventarioVariantDTO> BuscarProductoPorCombinacion(@PathVariable Long productoId, 
																			  @RequestParam String color, 
																			  @RequestParam String talla){
		
		return ResponseEntity.ok(inventarioService.findProductoByVariant(productoId, talla, color));
		
	}
	
	@GetMapping("/{productoId}/minimo")
	public ResponseEntity<List<InventarioProductoResponseDTO>> buscarProductoConStockMino(@PathVariable Long productoId,
																				  @RequestParam int minStock){
		
		return ResponseEntity.ok(inventarioService.findProductosLowStock(productoId, minStock));
						
	}
	
	@GetMapping("/{productoId}/talla")
	public ResponseEntity<List<InventarioResponseDTO>> listarInventarioPorProductoIdYTalla(@PathVariable Long productoId,
																						   @RequestParam String talla){
		
		return ResponseEntity.ok(inventarioService.findProductoAndTalla(productoId, talla));
		
	}
	
	@GetMapping("/{productoId}/color")
	public ResponseEntity<List<InventarioResponseDTO>> listarInventarioPorProductoIdYColor(@PathVariable Long productoId,
																						   @RequestParam String color){
		
		return ResponseEntity.ok(inventarioService.findProductoAndColor(productoId, color));
		
	}
	
	@PostMapping
	public ResponseEntity<InventarioResponseDTO> crearInventario(@RequestBody @Valid InventarioRequestDTO requestDTO){
		
			return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.crearInventario(requestDTO));
		
	}
	
	@PatchMapping("/reabastecer-producto")
	public ResponseEntity<List<InventarioVariantDTO>> reabastecerInventario(@RequestBody @Valid ReabastecerProductoDTO reabastecerDTO){
		
		return ResponseEntity.ok(inventarioService.reabastecerInventarioDeProducto(reabastecerDTO));
		
	}
	
	@DeleteMapping
    public ResponseEntity<Void> eliminar(
            @RequestParam Long productoId,
            @RequestParam String talla,
            @RequestParam String color) {
		
        inventarioService.eliminarInventario(productoId, talla, color);
        
        return ResponseEntity.noContent().build();
        
    }
	
}
