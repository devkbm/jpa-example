package com.example.demo.manytoone.set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/* ToString() 호출시 무한루프 제거를 위해 team 제외*/
@ToString(exclude = {"team"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
@Entity
public class Member  {	
		
	@Id		
	@Column(name="MEMBER_ID")
	String memberId;
	
	@ManyToOne
	@JoinColumn(name="TEAM_ID", nullable = false)
	Team team;
	
	@Column(name="MEMBER_NAME")
	String memberName;
				
	Member(Team team
	      ,String memberId 
	  	  ,String memberName) {		
		this.team = team;
		this.memberId = memberId;
		this.memberName = memberName;
	}
			
}
