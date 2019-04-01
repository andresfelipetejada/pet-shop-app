package com.tetsugaku.petshop.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tetsugaku.petshop.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
	
	Page<Factura> findByClienteId(Long clienteId, Pageable pageable);
	
}
