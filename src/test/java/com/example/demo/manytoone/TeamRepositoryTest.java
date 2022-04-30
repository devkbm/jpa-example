package com.example.demo.manytoone;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
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
	
	@DisplayName("팀 저장")
	@Test
	void save() {
		//Given
		Team team = new Team("test");
		
		//When
		repository.save(team);
				
		//Then
		assertThat(team.getTeamName()).isEqualTo("test");
	}
	
	@DisplayName("멤버 저장")
	@Test
	void saveMemberList() {
		//Given
		Team team = new Team("test");
		Member member1 = new Member(team);
		Member member2 = new Member(team);
		List<Member> memberList = Arrays.asList(member1,member2);
				
		//When
		team.addMemberList(memberList);
		repository.save(team);
				
		//Then
		assertThat(team.getTeamName()).isEqualTo("test");
		assertThat(team.getMembers().size()).isEqualTo(2);
		assertThat(team.getMembers().get(0)).isEqualTo(member1);
		assertThat(team.getMembers().get(1)).isEqualTo(member2);
		
	}
		
	@DisplayName("멤버 삭제")
	@Test
	void deleteMember() {
		//Given
		Team team = new Team("test");
		Member member1 = new Member(team);
		Member member2 = new Member(team);
		team.addMember(member1);
		team.addMember(member2);					
		repository.saveAndFlush(team);
		
		//When			
		team.deleteMember(1);
		repository.save(team);
		
		//Then
		assertThat(team.getTeamName()).isEqualTo("test");
		assertThat(team.getMembers().get(0)).isEqualTo(member1);
		assertThat(team.getMembers().size()).isEqualTo(1);
	}
}
