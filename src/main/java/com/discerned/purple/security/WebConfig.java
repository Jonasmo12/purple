//package com.discerned.purple.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000/", "http://localhost:3000")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .exposedHeaders("*")
//                .allowCredentials(true).maxAge(3600);
//
//        // Add more mappings...
//    }
//}
