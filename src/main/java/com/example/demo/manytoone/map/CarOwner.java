package com.example.demo.manytoone.map;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 소유자[1] - 차[N]				<br>
 * 소유자는 여러차를 소유할 수 있다.	<br>
 * 차의 소유자는 한명이다. 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "CAROWNER")
public class CarOwner {

	@Id		
	@Column(name="ID")
	String id;
	
	@Comment("소유자명")
	@Column(name="OWNER_NAME")
	String name;
		
	@OneToMany(mappedBy="owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "carNumber")
	Map<String, Car> carMap = new HashMap<>();
	
	CarOwner(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Car getCar(String carNumber) {
		return carMap.get(carNumber);
	}
	
	public void buyCar(String carNumber
			          ,String carKind) {
		this.carMap.put(carNumber, new Car(this, carNumber, carKind));		
	}
	
	public void sellCar(String carNumber) {				
		this.carMap.remove(carNumber);
	}
	
	public void changeCar(String carNumber, String newCarKind) {		
		Car newCar = new Car(this, carNumber, newCarKind);
		
		this.carMap.replace(carNumber, newCar);
	}
}
