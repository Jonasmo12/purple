package com.discerned.purple;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


public class Initializer extends SpringBootServletInitializer { 

	// @Override
	// public static void main(String[] args) {
	// 	SpringApplication.run(PurpleApplication.class, args);
	// }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(PurpleApplication.class);
	}
}
