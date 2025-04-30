package com.pipiris.tienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pipiris.tienda.dto.producto.ProductoRequestDTO;
import com.pipiris.tienda.dto.producto.ProductoResponseDTO;
import com.pipiris.tienda.mapper.ProductoMapper;
import com.pipiris.tienda.model.Categoria;
import com.pipiris.tienda.model.Marca;
import com.pipiris.tienda.model.Producto;
import com.pipiris.tienda.repository.CategoriaRepository;
import com.pipiris.tienda.repository.MarcaRepository;
import com.pipiris.tienda.repository.ProductoRepository;
import com.pipiris.tienda.service.ProductoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService{

	private final ProductoRepository productoRepo;
	private final ProductoMapper productoMap;
	private final CategoriaRepository categoriaRepo;
	private final MarcaRepository marcaRepo;

	

	public ProductoServiceImpl(ProductoRepository productoRepo, ProductoMapper productoMap,
			CategoriaRepository categoriaRepo, MarcaRepository marcaRepo) {
		this.productoRepo = productoRepo;
		this.productoMap = productoMap;
		this.categoriaRepo = categoriaRepo;
		this.marcaRepo = marcaRepo;
	}

	@Cacheable(value = "producto")
	@Override
	public List<ProductoResponseDTO> getAll() {
	
		return productoRepo.findAll().stream()
				.map(productoMap::enDTO)
				.collect(Collectors.toList());
		
	}

	@Override
	public ProductoResponseDTO getById(Long idProducto) {

		Producto producto = productoRepo.findById(idProducto)
		.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		return productoMap.enDTO(producto);

	}

	@Override
	public ProductoResponseDTO create(ProductoRequestDTO requestDTO) {

		Producto producto = productoMap.enModelo(requestDTO);
		
		Categoria categoria = categoriaRepo.findById(requestDTO.getCategoria())
		.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrado"));
		
		Marca marca = marcaRepo.findById(requestDTO.getMarca())
		.orElseThrow(() -> new EntityNotFoundException("Marca no encontrado"));
		
		producto.setCategoria(categoria);
		producto.setMarca(marca);
		
		Producto creado = productoRepo.save(producto);
		
		return productoMap.enDTO(creado);

	}

	@Override
	public ProductoResponseDTO update(Long idProducto, ProductoRequestDTO requestDTO) {
		
		Producto existente = productoRepo.findById(idProducto)
		.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		existente.setModelo(requestDTO.getModelo());
		existente.setPublico(requestDTO.getPublico());
		existente.setNombreImagen(requestDTO.getNombreImagen());
		existente.setCodigoBarra(requestDTO.getCodigoBarra());
		existente.setPrecioUnitario(requestDTO.getPrecioUnitario());
		
		Categoria categoria = categoriaRepo.findById(requestDTO.getCategoria())
				.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
		
		Marca marca = marcaRepo.findById(requestDTO.getMarca())
				.orElseThrow(() -> new EntityNotFoundException("Marca no encontrada"));
		
		existente.setCategoria(categoria);
		existente.setMarca(marca);

		Producto actualizado = productoRepo.save(existente);
		
		return productoMap.enDTO(actualizado);
		
	}

	@Override
	public void delete(Long idProducto) {

		Producto producto = productoRepo.findById(idProducto)
		.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		productoRepo.delete(producto);
		
	}

	@Override
	public List<ProductoResponseDTO> getProductoByModelo(String modelo) {

		return productoRepo.findByModeloContainingIgnoreCase(modelo)
				.stream()
				.map(productoMap::enDTO)
				.collect(Collectors.toList());
		
	}

}
