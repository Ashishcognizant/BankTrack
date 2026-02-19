package com.cts.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.dto.AuthResponse;
import com.cts.dto.LoginRequest;
import com.cts.dto.RegisterRequest;
import com.cts.dto.UserResponse;
import com.cts.model.User;
import com.cts.repository.UserRepository;
import com.cts.security.JwtService;
import com.cts.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

 private final UserService userService;
 private final UserRepository userRepository;
 private final JwtService jwtService;

 // Registration
 @PostMapping("/register")
 public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest req) {
     var created = userService.register(req);
     return ResponseEntity.ok(created);
 }

 // Email-only login (PoC because entity has no password field)
 @PostMapping("/login")
 public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
     User user = userRepository.findByEmail(req.getEmail().toLowerCase())
             .orElseThrow(() -> new IllegalArgumentException("User not found for email"));

     String token = jwtService.generateToken(
             user.getEmail(),
             Map.of("role", user.getRole().name(), "uid", user.getId())
     );

     return ResponseEntity.ok(new AuthResponse("Bearer", token, jwtService.getExpirationSeconds()));
 }
}