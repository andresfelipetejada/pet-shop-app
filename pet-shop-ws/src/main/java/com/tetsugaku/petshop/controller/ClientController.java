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
import com.tetsugaku.petshop.model.Client;
import com.tetsugaku.petshop.persistence.ClientRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {

	@Autowired
	private ClientRepository repository;
	
	@GetMapping("/client")
    @ApiOperation(value = "${client.search.value}", notes = "${client.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public List<Client> getAll() {
    	return repository.findAll();
    }
	
	@GetMapping("/client/{cedula}")
    @ApiOperation(value = "${client.id.search.value}", notes = "${client.id.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Optional<Client> getById(@PathVariable String cedula) {    	
		return repository.findByCedula(cedula);
    }
	
	@PostMapping("/client")
    @ApiOperation(value = "${client.create.value}", notes = "${client.create.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Client create(@Valid @RequestBody Client client) {    	
		return repository.save(client);
    }
	
	@PutMapping("/client/{id}")
    @ApiOperation(value = "${client.create.value}", notes = "${client.create.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Client update(@PathVariable Long id, @Valid @RequestBody Client dataRequest) {    	
		
		return repository.findById(id).map(client -> {
			client.setNombres(dataRequest.getNombres());
			client.setApellidos(dataRequest.getCedula());
			client.setTelefono(dataRequest.getTelefono());
			client.setEmail(dataRequest.getEmail());			
			return repository.save(client);
	    }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + id + " not found"));
	
    }
	
    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    	return repository.findById(id).map(client -> {
            repository.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ClientId " + id + " not found"));
    }
}
