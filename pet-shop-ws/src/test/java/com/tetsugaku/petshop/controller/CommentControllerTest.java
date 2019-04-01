package com.tetsugaku.petshop.controller;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tetsugaku.petshop.exception.ResourceNotFoundException;
import com.tetsugaku.petshop.model.Comment;
import com.tetsugaku.petshop.model.Post;
import com.tetsugaku.petshop.persistence.CommentRepository;
import com.tetsugaku.petshop.persistence.PostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommentControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private CommentController commentController;

	@Mock
	private CommentRepository commentRepository;
	
	@Mock
    private PostRepository postRepository;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(commentController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
	}

	
	@Test
	public void testGetAllCommentsByPostId() throws Exception {
		
		Long postId = new Long(100);
		Long commentId = new Long(100);
		
		Post post = new Post();
		post.setId(postId);
		post.setTitle("Post de test");
		post.setDescription("Este post se creo desde un test unitario");
		post.setContent("Este post se creo desde un test unitario");
		post.setCreatedAt(new Date());
		post.setUpdatedAt(new Date());
		
		Comment comment = new Comment();
		comment.setId(commentId);
		comment.setPost(post);
		comment.setText("Test unit");
		comment.setCreatedAt(new Date());
		comment.setUpdatedAt(new Date());
		List<Comment> content = new ArrayList<>(0);
		content.add(comment);
		
		Page<Comment> page = new PageImpl<Comment>(content);
		Pageable pageable = PageRequest.of(0, 20, Sort.unsorted());
		
		when(commentRepository.findByPostId(postId, pageable)).thenReturn(page);
		
		mockMvc.perform(get("/posts/{postId}/comments", postId)).andExpect(status().isOk());
		
		verify(commentRepository).findByPostId(postId, pageable);
	}

	@Test
	public void testCreateComment() throws Exception {
		
		
		Long postId = new Long(100);
		Long commentId = new Long(100);
		
		Post post = new Post();
		post.setId(postId);
		post.setTitle("Post de test");
		post.setDescription("Este post se creo desde un test unitario");
		post.setContent("Este post se creo desde un test unitario");
		post.setCreatedAt(new Date());
		post.setUpdatedAt(new Date());
		
		Comment comment = new Comment();
		comment.setId(commentId);
		comment.setPost(post);
		comment.setText("test unit");
		comment.setCreatedAt(new Date());
		comment.setUpdatedAt(new Date());
		List<Comment> content = new ArrayList<>(0);
		content.add(comment);
		
		Optional<Post> opt= Optional.of(post);
		
        when(postRepository.findById(100L)).thenReturn(opt);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
		
		mockMvc.perform(
					post("/posts/{postId}/comments", postId)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\"text\": \"test unit\"}")).andExpect(status().isOk())
				.andReturn();
		
		verify(postRepository).findById(100L);
		verify(commentRepository).save(any(Comment.class));
		
	}
	
	@Test
	public void testCreateCommentNotExistPost() throws Exception {
		
		Long postId = new Long(100);
        when(postRepository.findById(100L)).thenReturn(Optional.empty());
		
		mockMvc.perform(
					post("/posts/{postId}/comments", postId)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\"text\": \"test unit\"}")).andExpect(status().isNotFound())
				.andReturn();
	}

	@Test
	public void testUpdateComment() throws Exception {
		
		Long postId = new Long(100);
		Long commentId = new Long(100);
		
		Comment comment = new Comment();
		Optional<Comment> opt = Optional.of(comment);
		
		when(postRepository.existsById(100L)).thenReturn(true);
		when(commentRepository.findById(commentId)).thenReturn(opt);
		when(commentRepository.save(any(Comment.class))).thenReturn(comment);
		
		mockMvc.perform(
				put("/posts/{postId}/comments/{commentId}", postId, commentId)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{\"text\": \"test unit\"}")).andExpect(status().isOk())
			.andReturn();
		
		verify(postRepository).existsById(100L);
		verify(commentRepository).findById(commentId);
		verify(commentRepository).save(any(Comment.class));
		
	}
	
	@Test
	public void testUpdateCommentNotExistPost() throws Exception {
		
		Long postId = new Long(100);
		Long commentId = new Long(100);
		
		when(postRepository.existsById(100L)).thenReturn(false);
		
		mockMvc.perform(
				put("/posts/{postId}/comments/{commentId}", postId, commentId)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{\"text\": \"test unit\"}")).andExpect(status().isNotFound())
			.andReturn();
	}
	
	@Test
	public void testUpdateCommentNotExistComment() throws Exception {
		
		Long postId = new Long(100);
		Long commentId = new Long(100);
		
		when(postRepository.existsById(100L)).thenReturn(true);
		when(commentRepository.findById(commentId)).thenReturn(Optional.empty());
		
		mockMvc.perform(
				put("/posts/{postId}/comments/{commentId}", postId, commentId)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{\"text\": \"test unit\"}")).andExpect(status().isNotFound())
			.andReturn();
	}
	

	@Test
	public void testDeleteComment() throws Exception {
		
		Long postId = new Long(100);
		Long commentId = new Long(100);
		
		Comment comment = new Comment();
		Optional<Comment> opt = Optional.of(comment);
		
		when(commentRepository.findByIdAndPostId(commentId, postId)).thenReturn(opt);
		
		mockMvc.perform(delete("/posts/{postId}/comments/{commentId}", postId, commentId))
			.andExpect(status().isOk()).andReturn();
		
		verify(commentRepository).findByIdAndPostId(commentId, postId);
		verify(commentRepository).delete(any(Comment.class));

	}
	
	@Test
	public void testDeleteCommentNotExistComment() throws Exception {
		
		Long postId = new Long(100);
		Long commentId = new Long(100);
		
		when(commentRepository.findByIdAndPostId(commentId, postId)).thenReturn(Optional.empty());
		
		mockMvc.perform(delete("/posts/{postId}/comments/{commentId}", postId, commentId))
			.andExpect(status().isNotFound()).andReturn();
		
	}

}
