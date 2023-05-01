package com.discerned.purple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PurpleApplication extends SpringBootServletInitializer { 

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(PurpleApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PurpleApplication.class, args);
	}

	
}
