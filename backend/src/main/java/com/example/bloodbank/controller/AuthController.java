
package com.example.bloodbank.controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

@PostMapping("/login")
public Map<String,String> login(@RequestBody Map<String,String> body){
    return Map.of("message","Login successful","user",body.get("username"));
}

@PostMapping("/register")
public Map<String,String> register(@RequestBody Map<String,String> body){
    return Map.of("message","User registered");
}
}
