package com.tetsugaku.petshop.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tetsugaku.petshop.exception.ResourceNotFoundException;
import com.tetsugaku.petshop.model.Detalle;
import com.tetsugaku.petshop.model.Factura;
import com.tetsugaku.petshop.model.FacturaDTO;
import com.tetsugaku.petshop.model.Product;
import com.tetsugaku.petshop.persistence.ClientRepository;
import com.tetsugaku.petshop.persistence.DetalleRepository;
import com.tetsugaku.petshop.persistence.FacturaRepository;
import com.tetsugaku.petshop.persistence.ProductRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class FacturaController {

	@Autowired
    private FacturaRepository facturaRepository;
	
	@Autowired
    private DetalleRepository detalleRepository;

	@Autowired
    private ClientRepository clienteRepository;
	
	@Autowired
    private ProductRepository productRepository;

	@GetMapping("/factura/{idfactura}")
	@ApiOperation(value = "${factura.search.value}", notes = "${factura.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Factura getById(@PathVariable (value = "idfactura") Long idfactura) {
    	return facturaRepository.findById(idfactura).map(factura -> {
            return factura;
        }).orElseThrow(() -> new ResourceNotFoundException("FacturaId " + idfactura + " not found"));
    }
	
	@GetMapping("/facturas/{idcliente}")
	@ApiOperation(value = "${facturas.search.value}", notes = "${facturas.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Page<Factura> getByIdCliente(@PathVariable (value = "idcliente") Long idcliente, @PageableDefault(size = 20, value = 0) Pageable pageable) {
    	
		return facturaRepository.findByClienteId(idcliente, pageable);
    }
	
	@PostMapping("/factura/{idcliente}")
    @ApiOperation(value = "${factura.create.value}", notes = "${factura.create.notes}")
    public Factura crear(@PathVariable (value = "idcliente") String idcliente, @Valid @RequestBody FacturaDTO requestFactura) {
		
		return clienteRepository.findByCedula(idcliente).map(client-> {
			
			Factura factura = new Factura();
			factura.setCliente(client);
			factura.setFecha(new Date());
			requestFactura.getItems().forEach(p-> {
				Detalle e = new Detalle();				
				Product product = productRepository.getOne(p.getId());
				e.setProducto(product);					
				e.setImpuesto(0.0);				
				e.setValor(p.getPrice());
				e.setCantidad(p.getQuantity());
				e.setTotal(p.total());
				
				factura.addDetalle(e);
			});
			return facturaRepository.save(factura);						
		}).orElseThrow(() -> new ResourceNotFoundException("idcliente " + idcliente + " not found"));
    }
	
	@PutMapping("/factura/{idfactura}")
    @ApiOperation(value = "${factura.update.value}", notes = "${factura.update.notes}")
    public Factura actualizar(@PathVariable (value = "idfactura") Long idfactura, @Valid @RequestBody Factura factura) {
		
		return facturaRepository.save(factura);
	}
	
}
