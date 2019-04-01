package com.tetsugaku.petshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tetsugaku.petshop.model.Cliente;
import com.tetsugaku.petshop.persistence.ClienteRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository repository;
	
    @GetMapping("/clientes")
    @ApiOperation(value = "${cliente.search.value}", notes = "${cliente.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Page<Cliente> getAll(@PageableDefault(size = 20, value = 0) Pageable pageable) {
    	return repository.findAll(pageable);
    }
    
    @PostMapping("/clientes")
    @ApiOperation(value = "${cliente.create.value}", notes = "${post.create.notes}")
    public Cliente create(@Valid @RequestBody Cliente obj) {
    	return repository.save(obj);
    }
}
