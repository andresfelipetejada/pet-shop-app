package com.tetsugaku.petshop.controller;

import java.util.List;

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
import com.tetsugaku.petshop.model.Product;
import com.tetsugaku.petshop.persistence.CategoryRepository;
import com.tetsugaku.petshop.persistence.ProductRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/product")
    @ApiOperation(value = "${product.search.value}", notes = "${product.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public List<Product> getAllProducts() {
    	return repository.findAll();
    }

	@GetMapping("/product/{productId}")
    @ApiOperation(value = "${product.search.value}", notes = "${product.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Product getProduct(Long productId) {
		return repository.findById(productId).get();
    }
	
    @PostMapping("/product/{categoryId}")
    @ApiOperation(value = "${product.create.value}", notes = "${product.create.notes}")
    public Product createProduct(@PathVariable (value = "categoryId") Long categoryId, @Valid @RequestBody Product product) {
    	return categoryRepository.findById(categoryId).map(category -> {
    		product.setCategory(category);    		
            return repository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("categoryId " + categoryId + " not found"));
    }
     
    @PutMapping("/product/{productId}")
    public Product updateProduct(@PathVariable Long productId, @Valid @RequestBody Product productRequest) {
    	return repository.findById(productId).map(product -> {
	        product.setName(productRequest.getName());
	        product.setDescription(productRequest.getDescription());
	        product.setPrice(productRequest.getPrice());
	        product.setSku(productRequest.getSku());
	        product.setStock(productRequest.getStock());
	        product.setImage(productRequest.getImage());
	        product.setCategory(productRequest.getCategory());
	        return repository.save(product);
	    }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
    	return repository.findById(productId).map(product -> {
            repository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }

}
