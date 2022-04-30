package com.example.demo.manytoone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "TEAM")
public class Team  {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEAM_ID")
	Long teamId;
	
	@Comment("팀명")
	@Column(name="TEAM_NAME")
	String teamName;	
		
	//@OneToMany(mappedBy="team")
	@OneToMany(mappedBy="team", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Member> members = new ArrayList<Member>();			
	
	public Team(String teamName) {
		this.teamName = teamName;		
	}	
	
	public void modify(String teamName) {
		this.teamName = teamName;
	}						
	
	public void addMemberList(List<Member> memberList) {
		this.members.addAll(memberList);
	}
	
	public void deleteMember(int index) {
		this.members.remove(index);
	}
	
	
}
