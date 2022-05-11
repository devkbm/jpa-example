package com.example.demo.valuecollection.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class BlogCollectionRepositoryTest {

	@Autowired
	private BlogCollectionRepository repository;
	
	@DisplayName("블로그 저장")
	@Test
	void saveOwner() {
		//Given
		BlogCollection blog = new BlogCollection("JPA학습");
		
		//When
		repository.save(blog);
		log.info(blog.toString());		
		//Then
		assertThat(blog.getBlogName()).isEqualTo("JPA학습");
	}
	
	
	@DisplayName("댓글 저장")
	@Test
	void saveComment() {
		//Given
		BlogCollection blog = new BlogCollection("JPA학습");		
		
		//When
		blog.addComment(new BlogCollectionComment("댓글1"));
		blog.addComment(new BlogCollectionComment("댓글2"));		
		repository.saveAndFlush(blog);
		log.info(blog.toString());
		
		//Then
		assertThat(blog.getBlogName()).isEqualTo("JPA학습");
		assertThat(blog.getComments().size()).isEqualTo(2);
		assertThat(blog.getComments().get(0).getComment()).isEqualTo("댓글1");
		assertThat(blog.getComments().get(1).getComment()).isEqualTo("댓글2");
	}
	
	
	@DisplayName("댓글 삭제")
	@Test
	void deleteComment() {
		//Given
		BlogCollection blog = new BlogCollection("JPA학습");
		blog.addComment(new BlogCollectionComment("댓글1"));
		blog.addComment(new BlogCollectionComment("댓글2"));
		repository.saveAndFlush(blog);
								
		//When
		blog.deleteComment(blog.getComments().get(0));		
		repository.saveAndFlush(blog);
		log.info(blog.toString());
		
		//Then
		assertThat(blog.getBlogName()).isEqualTo("JPA학습");
		assertThat(blog.getComments().size()).isEqualTo(1);		
		assertThat(blog.getComments().get(0).getComment()).isEqualTo("댓글2");
	}
	
}
