package com.example.demo.valuecollection.map;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Comment;

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
public class CarCollection {
			
	@Comment("차량종류")
	@Column(name="CAR_KIND")
	String carKind;
				
	CarCollection(String carKind) {				
		this.carKind = carKind;		
	}
}
