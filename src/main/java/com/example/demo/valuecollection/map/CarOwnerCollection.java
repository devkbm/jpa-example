package com.example.demo.valuecollection.map;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
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
@Table(name = "CAROWNER_COLLECTION")
public class CarOwnerCollection {

	@Id		
	@Column(name="ID")
	String id;
	
	@Comment("소유자명")
	@Column(name="OWNER_NAME")
	String name;
		
	@ElementCollection
    @CollectionTable(
        name = "CAR_COLLECTION", 
        joinColumns = @JoinColumn(name = "ONWER_ID")
    )
    @MapKeyColumn(name = "carNumber")
	Map<String, CarCollection> carMap = new HashMap<>();
	
	CarOwnerCollection(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CarCollection getCar(String carNumber) {
		return carMap.get(carNumber);
	}
	
	public void buyCar(String carNumber
			          ,String carKind) {
		this.carMap.put(carNumber, new CarCollection(carKind));		
	}
	
	public void sellCar(String carNumber) {				
		this.carMap.remove(carNumber);
	}
	
	public void changeCar(String carNumber, String newCarKind) {		
		CarCollection newCar = new CarCollection(newCarKind);
		
		this.carMap.replace(carNumber, newCar);
	}
}
