package com.ncookie.harumvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HaruMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaruMvcApplication.class, args);
	}

}
