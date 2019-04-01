package com.tetsugaku.petshop.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tetsugaku.petshop.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	Page<Producto> findByCategoriaId(Long categoriaId, Pageable pageable);

}
