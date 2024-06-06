package com.th.mux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@Slf4j
public class BackendApplication {

	public static void main(String[] args) {
		// testing
		//LocalDate date = LocalDate.now();
		//log.info("testing current date = {}", date);
		SpringApplication.run(BackendApplication.class, args);
	}

}
