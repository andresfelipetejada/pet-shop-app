package com.tetsugaku.petshop.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tetsugaku.petshop.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
