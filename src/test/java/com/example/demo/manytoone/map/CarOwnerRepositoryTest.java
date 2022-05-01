package com.example.demo.manytoone.map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class CarOwnerRepositoryTest {

	@Autowired
	private CarOwnerRepository repository;
	
	@DisplayName("차량소유자 저장")
	@Test
	void saveOwner() {
		//Given
		CarOwner owner = new CarOwner("A", "TEST");
		
		//When
		repository.save(owner);
				
		//Then
		assertThat(owner.getId()).isEqualTo("A");
		assertThat(owner.getName()).isEqualTo("TEST");
	}
	
	@DisplayName("차량 구매")
	@Test
	void saveBuyCar() {
		//Given
		CarOwner owner = new CarOwner("A", "TEST");
		
		//When
		owner.buyCar("서울호3933","QM6");
		repository.save(owner);
		log.info(owner.toString());
		//Then
		assertThat(owner.getId()).isEqualTo("A");
		assertThat(owner.getName()).isEqualTo("TEST");
		assertThat(owner.getCar("서울호3933").getCarKind()).isEqualTo("QM6");
		assertThat(owner.getCar("서울호3933").getCarNumber()).isEqualTo("서울호3933");
		assertThat(owner.carMap.size()).isEqualTo(1);
	}
	
	@DisplayName("차량 판매")
	@Test
	void saveSellCar() {
		//Given
		CarOwner owner = new CarOwner("A", "TEST");
		owner.buyCar("서울호3933","QM6");
		owner.buyCar("서울호4444","스포티지");
		repository.saveAndFlush(owner);
		
		//When
		owner.sellCar("서울호4444");
		repository.saveAndFlush(owner);
		log.info(owner.toString());
		//Then
		assertThat(owner.getId()).isEqualTo("A");
		assertThat(owner.getName()).isEqualTo("TEST");
		assertThat(owner.getCar("서울호3933").getCarNumber()).isEqualTo("서울호3933");
		assertThat(owner.getCar("서울호3933").getCarKind()).isEqualTo("QM6");		
		assertThat(owner.carMap.size()).isEqualTo(1);	
	}
	
	@DisplayName("차량 교환")
	@Test
	void saveChagngeCar() {
		//Given
		CarOwner owner = new CarOwner("A", "TEST");
		owner.buyCar("서울호3933","QM6");		
		repository.saveAndFlush(owner);
		
		//When
		owner.changeCar("서울호3933", "스포티지");		
		repository.saveAndFlush(owner);
		log.info(owner.toString());
		//Then
		assertThat(owner.getId()).isEqualTo("A");
		assertThat(owner.getName()).isEqualTo("TEST");
		assertThat(owner.getCar("서울호3933").getCarNumber()).isEqualTo("서울호3933");
		assertThat(owner.getCar("서울호3933").getCarKind()).isEqualTo("스포티지");
		
	}
	
	
}
