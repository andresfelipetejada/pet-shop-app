package com.tetsugaku.petshop.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tetsugaku.petshop.exception.ResourceNotFoundException;
import com.tetsugaku.petshop.model.Categoria;
import com.tetsugaku.petshop.persistence.CategoriaRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping("/categoria")
    @ApiOperation(value = "${categoria.search.value}", notes = "${categoria.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Page<Categoria> getAll(@PageableDefault(size = 20, value = 0) Pageable pageable) {
    	return repository.findAll(pageable);
    }
	
	@GetMapping("/categoria/{id}")
    @ApiOperation(value = "${categoria.id.search.value}", notes = "${categoria.id.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Optional<Categoria> getById(Long id) {    	
		return repository.findById(id);
    }
	
	@PostMapping("/categoria")
    @ApiOperation(value = "${categoria.create.value}", notes = "${categoria.create.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Categoria create(@Valid @RequestBody Categoria categoria) {    	
		return repository.save(categoria);
    }
	
	@PutMapping("/categoria/{id}")
    @ApiOperation(value = "${categoria.create.value}", notes = "${categoria.create.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Categoria update(Long id, @Valid @RequestBody Categoria categoriaRequest) {    	
		
		return repository.findById(id).map(categoria -> {
			categoria.setNombre(categoriaRequest.getNombre());
			categoria.setDescripcion(categoria.getDescripcion());
			categoria.setUrlImagen(categoria.getUrlImagen());
			return repository.save(categoria);
	    }).orElseThrow(() -> new ResourceNotFoundException("CategoriaId " + id + " not found"));
	
    }
	
}
