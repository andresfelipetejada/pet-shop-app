package com.tetsugaku.petshop.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.hamcrest.Matchers.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tetsugaku.petshop.exception.ResourceNotFoundException;
import com.tetsugaku.petshop.model.Post;
import com.tetsugaku.petshop.persistence.PostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PostControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private PostController postController;

	@Mock
	private PostRepository repository;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(postController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
	}

	@Test
	public void getAllPosts() throws Exception {

		Post post = new Post();
		post.setId(0L);
		post.setTitle("Post de test");
		post.setDescription("Este post se creo desde un test unitario");
		post.setContent("Este post se creo desde un test unitario");
		post.setCreatedAt(new Date());
		post.setUpdatedAt(new Date());
		List<Post> content = new ArrayList<>(0);
		content.add(post);
		Page<Post> page = new PageImpl<Post>(content);

		Pageable pageable = PageRequest.of(0, 20, Sort.unsorted());

		when(repository.findAll(pageable)).thenReturn(page);

		mockMvc.perform(get("/posts", pageable)).andExpect(status().isOk());

		verify(repository).findAll(pageable);

	}

	@Test
	public void createPost() throws Exception {

		String jsonPost = "{\"title\":\"Post de test\",\"description\":\"test unitario\",\"content\":\"test unitario\"}";

		Post postReturn = new Post();
		postReturn.setId(100L);
		postReturn.setTitle("Post de test");
		postReturn.setDescription("test unitario");
		postReturn.setContent("test unitario");
		postReturn.setCreatedAt(new Date());
		postReturn.setUpdatedAt(new Date());

		when(repository.save(any(Post.class))).thenReturn(postReturn);

		mockMvc.perform(post("/posts").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonPost))
				.andExpect(status().isOk()).andReturn();

		verify(repository).save(any(Post.class));

	}
	
	@Test
	public void updatePost() throws Exception {

		String jsonPost = "{\"id\":100, \"title\":\"Post de test\",\"description\":\"test unitario\",\"content\":\"test unitario\"}";

		Post postReturn = new Post();
		postReturn.setId(100L);
		postReturn.setTitle("Post de test");
		postReturn.setDescription("test unitario");
		postReturn.setContent("test unitario");
		postReturn.setCreatedAt(new Date());
		postReturn.setUpdatedAt(new Date());
		Optional<Post> opt= Optional.of(postReturn);
		
		when(repository.findById(100L)).thenReturn(opt);
		when(repository.save(any(Post.class))).thenReturn(postReturn);
		
		mockMvc.perform(put("/posts/{postId}", 100L)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(jsonPost))
					.andExpect(status().isOk()).andReturn();

		verify(repository).findById(100L);
		verify(repository).save(any(Post.class));

	}
	
	@Test
	public void updatePostNotExist() throws Exception {
		
		String jsonPost = "{\"id\":100, \"title\":\"Post de test\",\"description\":\"test unitario\",\"content\":\"test unitario\"}";
		//ResourceNotFoundException exception = new ResourceNotFoundException("");
	    
		when(repository.findById(100L)).thenReturn(Optional.empty());
	    
		ResultActions result = mockMvc.perform(put("/posts/{postId}", 100L).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonPost));
	    result.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void deletePost() throws Exception {

		Post postReturn = new Post();
		postReturn.setId(100L);
		postReturn.setTitle("Post de test");
		postReturn.setDescription("test unitario");
		postReturn.setContent("test unitario");
		postReturn.setCreatedAt(new Date());
		postReturn.setUpdatedAt(new Date());
		Optional<Post> opt= Optional.of(postReturn);
		
		when(repository.findById(100L)).thenReturn(opt);
		
		mockMvc.perform(delete("/posts/{postId}", 100L)).andExpect(status().isOk()).andReturn();

		verify(repository).findById(100L);
		verify(repository).delete(any(Post.class));

	}
	
	@Test
	public void deletePostNotExist() throws Exception {

		when(repository.findById(100L)).thenReturn(Optional.empty());
	    
		ResultActions result = mockMvc.perform(delete("/posts/{postId}", 100L));
	    result.andExpect(status().isNotFound());

	}

}
