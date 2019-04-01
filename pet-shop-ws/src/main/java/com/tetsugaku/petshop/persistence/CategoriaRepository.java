package com.tetsugaku.petshop.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tetsugaku.petshop.model.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{

}
