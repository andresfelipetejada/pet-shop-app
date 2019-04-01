package com.tetsugaku.petshop.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tetsugaku.petshop.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    
	Page<Mascota> findByClienteId(Long clienteId, Pageable pageable);
    
    Optional<Mascota> findByIdAndClienteId(Long id, Long clienteId);
}