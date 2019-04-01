package com.tetsugaku.petshop.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tetsugaku.petshop.model.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {

}