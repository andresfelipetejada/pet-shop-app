package com.tetsugaku.petshop.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tetsugaku.petshop.model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>{

}
