package com.discerned.purple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PurpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurpleApplication.class, args);
	}


	@RequestMapping("")
	public String hello() {
		return "hello from azure for discerned";
	}

}
