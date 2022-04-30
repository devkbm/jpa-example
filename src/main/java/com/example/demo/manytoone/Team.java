package com.example.demo.manytoone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	/*@OneToOne
	@JoinColumn(name="USER_ID")
	private User manager;*/ 
		
	//@OneToMany(mappedBy="team")
	@OneToMany(mappedBy="team", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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
	
}
