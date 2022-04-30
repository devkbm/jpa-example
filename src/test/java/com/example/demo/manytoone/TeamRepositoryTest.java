package com.example.demo.manytoone;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class TeamRepositoryTest {

	@Autowired
	private TeamRepository repository;
	
	@Test
	void save() {
		//Given
		Team team = new Team("test");
		
		//When
		repository.save(team);
				
		//Then
		assertThat(team.getTeamName()).isEqualTo("test");
	}
}
