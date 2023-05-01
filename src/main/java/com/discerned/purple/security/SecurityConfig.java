//package com.discerned.discerneded.security;
//
//import com.discerned.discerneded.patient.PatientService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//    private final PatientService patientService;
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public SecurityConfig(
//            PatientService patientService,
//            BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.patientService = patientService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
////                .csrf().disable()
////                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers("/patient/new", "/patient/save").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .userDetailsService(patientService)
////                .headers(headers -> headers.frameOptions().sameOrigin())
//                .build();
//    }
//
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(patientService);
//        return provider;
//    }
//
//}
