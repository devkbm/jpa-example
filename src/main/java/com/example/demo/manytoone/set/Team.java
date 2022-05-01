package com.example.demo.manytoone.set;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 팀[1] - 팀원[N]				<br>
 * 팀은 여러팀원을 가질수 있다. 		<br>
 * 팀원은 한팀에만 소속 될 수 있다. 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "TEAM")
public class Team  {
	
	@Id		
	@Column(name="TEAM_ID")
	String teamId;
	
	@Comment("팀명")
	@Column(name="TEAM_NAME")
	String teamName;
		
	@OneToMany(mappedBy="team", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<Member> members = new HashSet<Member>();			
	
	public Team(String teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;		
	}	
	
	public void modify(String teamName) {
		this.teamName = teamName;
	}						
	
	public Member getMember(String memberId) {
		Member member = null;
		
		// java 7
		/*
		for (Member m : this.members) {
			if (m.memberId.equals(memberId))
			{
				member = m;
				break;
			}
		}
		*/
		// java stream
		member = this.members.stream()
				             .filter(e -> e.memberId.equals(memberId))
							 .findFirst().orElse(null);
		
		return member;
	}
	
	public void joinMember(String memberId, String memberName) {
		this.getMembers().add(new Member(this, memberId, memberName));
	}	
	
	public void leaveMember(Member member) {
		this.getMembers().remove(member);
	}
	
	
}
