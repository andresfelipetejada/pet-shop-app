package com.tetsugaku.petshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tetsugaku.petshop.exception.ResourceNotFoundException;
import com.tetsugaku.petshop.model.Post;
import com.tetsugaku.petshop.persistence.PostRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class PostController {

    @Autowired
    private PostRepository repository;

    @GetMapping("/posts")
    @ApiOperation(value = "${post.search.value}", notes = "${post.search.notes}")
    @ApiResponse(code = 200, message = "Se devolvieron resultados en la busqueda")    
    public Page<Post> getAllPosts(@PageableDefault(size = 20, value = 0) Pageable pageable) {
    	return repository.findAll(pageable);
    }

    @PostMapping("/posts")
    @ApiOperation(value = "${post.update.value}", notes = "${post.update.notes}")
    public Post createPost(@Valid @RequestBody Post post) {
    	return repository.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
    	return repository.findById(postId).map(post -> {
	        post.setTitle(postRequest.getTitle());
	        post.setDescription(postRequest.getDescription());
	        post.setContent(postRequest.getContent());
	        return repository.save(post);
	    }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
    	return repository.findById(postId).map(post -> {
            repository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

}