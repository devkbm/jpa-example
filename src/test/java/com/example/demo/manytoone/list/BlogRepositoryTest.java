package com.example.demo.manytoone.list;

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
public class BlogRepositoryTest {

	@Autowired
	private BlogRepository repository;
	
	@DisplayName("블로그 저장")
	@Test
	void saveOwner() {
		//Given
		Blog blog = new Blog("JPA학습");
		
		//When
		repository.save(blog);
		log.info(blog.toString());		
		//Then
		assertThat(blog.getBlogName()).isEqualTo("JPA학습");
	}
}
