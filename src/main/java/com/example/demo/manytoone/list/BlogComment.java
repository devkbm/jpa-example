package com.example.demo.manytoone.list;

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
@Table(name = "BOARD")
@Entity
public class BlogComment  {	
		
	@Id		
	@Column(name="MEMBER_ID")
	String memberId;
	
	@ManyToOne
	@JoinColumn(name="TEAM_ID", nullable = false)
	Blog team;
	
	@Column(name="MEMBER_NAME")
	String memberName;
				
	BlogComment(Blog team
	      ,String memberId 
	  	  ,String memberName) {		
		this.team = team;
		this.memberId = memberId;
		this.memberName = memberName;
	}
			
}
