package com.wskang.trip;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// (exclude = { SecurityAutoConfiguration.class }) : 어노테이션에서 spring security 제외
@SpringBootApplication
public class TripBackendApplication {

	// Dto 와 Entity 간 변환을 담당
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		// 구글 클라우드 일반계정 전환 후 public ip 변경
		SpringApplication.run(TripBackendApplication.class, args);
	}
}
