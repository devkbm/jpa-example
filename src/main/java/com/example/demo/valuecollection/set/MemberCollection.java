package com.example.demo.valuecollection.set;

import javax.persistence.Column;
import javax.persistence.Embeddable;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MemberCollection {
	
	@Column(name="MEMBER_ID")
	String memberId;	
	
	@Column(name="MEMBER_NAME")
	String memberName;
				
	MemberCollection(String memberId 
				  	,String memberName) {				
		this.memberId = memberId;
		this.memberName = memberName;
	}
	
}
