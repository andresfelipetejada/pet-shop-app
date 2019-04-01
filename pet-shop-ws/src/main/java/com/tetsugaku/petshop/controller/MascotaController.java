package com.tetsugaku.petshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tetsugaku.petshop.exception.ResourceNotFoundException;
import com.tetsugaku.petshop.model.Mascota;
import com.tetsugaku.petshop.persistence.ClienteRepository;
import com.tetsugaku.petshop.persistence.MascotaRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class MascotaController {

	@Autowired
    private ClienteRepository clienteRepository;
	
	@Autowired
    private MascotaRepository repository;
	
    @GetMapping("/mascotas")
    @ApiOperation(value = "${mascota.search.value}", notes = "${mascota.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Page<Mascota> getAll(@PageableDefault(size = 20, value = 0) Pageable pageable) {
    	return repository.findAll(pageable);
    }
    
    @PostMapping("/mascotas/{idcliente}")
    @ApiOperation(value = "${mascota.create.value}", notes = "${mascota.create.notes}")
    public Mascota create(@PathVariable (value = "idcliente") Long idcliente, @Valid @RequestBody Mascota mascota) {
    	
    	return clienteRepository.findById(idcliente).map(cliente -> {
    		mascota.setCliente(cliente);
            return repository.save(mascota);
        }).orElseThrow(() -> new ResourceNotFoundException("Cliente " + idcliente + " not found"));
    	
    }
}
