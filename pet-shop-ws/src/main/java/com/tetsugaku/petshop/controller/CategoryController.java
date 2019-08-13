package com.tetsugaku.petshop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tetsugaku.petshop.exception.ResourceNotFoundException;
import com.tetsugaku.petshop.model.Category;
import com.tetsugaku.petshop.persistence.CategoryRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryRepository repository;
	
	@GetMapping("/category")
    @ApiOperation(value = "${category.search.value}", notes = "${category.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public List<Category> getAll() {
    	return repository.findAll();
    }
	
	@GetMapping("/category/{id}")
    @ApiOperation(value = "${category.id.search.value}", notes = "${category.id.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Optional<Category> getById(@PathVariable Long id) {    	
		return repository.findById(id);
    }
	
	@PostMapping("/category")
    @ApiOperation(value = "${category.create.value}", notes = "${category.create.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Category create(@Valid @RequestBody Category category) {    	
		return repository.save(category);
    }
	
	@PutMapping("/category/{id}")
    @ApiOperation(value = "${category.create.value}", notes = "${category.create.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Category update(@PathVariable Long id, @Valid @RequestBody Category dataRequest) {    	
		
		return repository.findById(id).map(category -> {
			category.setName(dataRequest.getName());
			category.setDescription(dataRequest.getDescription());
			category.setUrlImage(dataRequest.getUrlImage());
			
			return repository.save(category);
	    }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + id + " not found"));
	
    }
	
    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    	return repository.findById(id).map(category -> {
            repository.delete(category);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + id + " not found"));
    }
	
}
