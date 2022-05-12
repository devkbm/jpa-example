package com.example.demo.valuecollection.set;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "TEAM_COLLECTION")
public class TeamCollection {

	@Id		
	@Column(name="TEAM_ID")
	String teamId;
	
	@Comment("팀명")
	@Column(name="TEAM_NAME")
	String teamName;
			
	@ElementCollection
    @CollectionTable(
        name = "MEMBER_COLLECTION", 
        joinColumns = @JoinColumn(name = "TEAM_ID")
    )
	Set<MemberCollection> members = new HashSet<MemberCollection>();			
	
	public TeamCollection(String teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;		
	}	
	
	public void modify(String teamName) {
		this.teamName = teamName;
	}						
	
	public MemberCollection getMember(String memberId) {
		MemberCollection member = null;
		
		// java 7		
		for (MemberCollection m : this.members) {
			if (m.memberId.equals(memberId))
			{
				member = m;
				break;
			}
		}		
		
		return member;
	}
	
	public void joinMember(String memberId, String memberName) {
		this.getMembers().add(new MemberCollection(memberId, memberName));
	}	
	
	public void leaveMember(MemberCollection member) {
		this.getMembers().remove(member);
	}
}
