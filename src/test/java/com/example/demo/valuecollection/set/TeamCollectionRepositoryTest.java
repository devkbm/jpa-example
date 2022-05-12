package com.example.demo.valuecollection.set;

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
public class TeamCollectionRepositoryTest {

	@Autowired
	private TeamCollectionRepository repository;
	
	@DisplayName("팀 저장")
	@Test
	void save() {
		//Given
		TeamCollection team = new TeamCollection("TEAMA", "test");
		
		//When
		repository.saveAndFlush(team);
				
		//Then
		assertThat(team.getTeamId()).isEqualTo("TEAMA");
		assertThat(team.getTeamName()).isEqualTo("test");
	}
	
	@DisplayName("팀 가입")
	@Test
	void saveMemberList() {
		//Given
		TeamCollection team = new TeamCollection("TEAMA", "test");
		team.joinMember("A", "멤버A");
		team.joinMember("B", "멤버B");					
				
		//When		
		repository.saveAndFlush(team);
				
		//Then
		assertThat(team.getTeamId()).isEqualTo("TEAMA");
		assertThat(team.getTeamName()).isEqualTo("test");
		assertThat(team.getMembers().size()).isEqualTo(2);
		assertThat(team.getMember("A").getMemberName()).isEqualTo("멤버A");
		assertThat(team.getMember("B").getMemberName()).isEqualTo("멤버B");				
	}
				
	@DisplayName("팀 탈퇴")
	@Test
	void deleteMember() {
		//Given
		TeamCollection team = new TeamCollection("TEAMA", "test");		
		team.joinMember("A", "멤버A");
		team.joinMember("B", "멤버B");						
		repository.saveAndFlush(team);
		
		log.info(team.toString());
		team.leaveMember(team.getMember("B"));
		//When					
		repository.saveAndFlush(team);
		log.info(team.toString());
		//Then
		assertThat(team.getTeamId()).isEqualTo("TEAMA");
		assertThat(team.getTeamName()).isEqualTo("test");		
		assertThat(team.getMembers().size()).isEqualTo(1);
	}
}
