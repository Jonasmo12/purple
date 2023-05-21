//package com.discerned.purple.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//    @PostMapping("/registration")
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {
//        return ResponseEntity.ok(userService.authenticate(user));
//    }
//}
