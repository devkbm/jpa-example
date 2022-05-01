package com.example.demo.manytoone.map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/* ToString() 호출시 무한루프 제거를 위해 제외*/
@ToString(exclude = {"owner"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CAR")
@Entity
public class Car {
	
	@Id
	@Comment("차량번호")
	@Column(name="CAR_NO")
	String carNumber;
	
	@Comment("차량종류")
	@Column(name="CAR_KIND")
	String carKind;
			
	@ManyToOne
	@JoinColumn(name="ONWER_ID", nullable = false)
	CarOwner owner;
	
	Car(CarOwner owner
	   ,String carNumber
	   ,String carKind) {
		this.owner = owner;
		this.carNumber = carNumber;
		this.carKind = carKind;		
	}
}
