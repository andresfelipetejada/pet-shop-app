package com.tetsugaku.petshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tetsugaku.petshop.exception.ResourceNotFoundException;
import com.tetsugaku.petshop.model.Producto;
import com.tetsugaku.petshop.persistence.CategoriaRepository;
import com.tetsugaku.petshop.persistence.ProductoRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("/productos")
    @ApiOperation(value = "${producto.search.value}", notes = "${producto.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Page<Producto> getAllProductos(@PageableDefault(size = 20, value = 0) Pageable pageable) {
    	return productoRepository.findAll(pageable);
    }

	@GetMapping("/productos/{categoriaId}")
    @ApiOperation(value = "${producto.categoria.search.value}", notes = "${producto.categoria.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Page<Producto> getAllProductosPorCategoria(@PathVariable (value = "categoriaId") Long categoriaId, @PageableDefault(size = 20, value = 0) Pageable pageable) {
    	return productoRepository.findByCategoriaId(categoriaId, pageable);
    }
	
	@GetMapping("/producto/{idproducto}")
    @ApiOperation(value = "${producto.search.value}", notes = "${producto.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Producto getProductoPorId(@PathVariable (value = "idproducto") Long idproducto) {
    	return productoRepository.findById(idproducto)
    			.map(producto -> {
    				return producto;
    			})
    			.orElseThrow(() -> new ResourceNotFoundException("idproducto " + idproducto + " not found"));
    }
	
	
    @PostMapping("/productos/{categoriaId}")
    @ApiOperation(value = "${producto.create.value}", notes = "${producto.create.notes}")
    public Producto createProducto(@PathVariable (value = "categoriaId") Long categoriaId, @Valid @RequestBody Producto producto) {    	
    	return categoriaRepository.findById(categoriaId).map(categoria -> {
    		producto.setCategoria(categoria);
            return productoRepository.save(producto);
        }).orElseThrow(() -> new ResourceNotFoundException("CategoriaId " + categoriaId + " not found"));    	
    }
    
    @PostMapping("/productos")
    @ApiOperation(value = "${producto.create.value}", notes = "${producto.create.notes}")
    public List<Producto> createProducto(@Valid @RequestBody List<Producto> productos) {
    	
    	return productoRepository.saveAll(productos);    
    
    }
    
	
}
