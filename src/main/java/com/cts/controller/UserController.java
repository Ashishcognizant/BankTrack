package com.cts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.dto.UserResponse;
import com.cts.repository.UserRepository;
import com.cts.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

 private final UserService userService;
 private final UserRepository userRepository;

 // Profile for the logged-in user
 @GetMapping("/me")
 public ResponseEntity<UserResponse> me(Principal principal) {
     var user = userRepository.findByEmail(principal.getName()).orElseThrow();
     return ResponseEntity.ok(userService.toResponse(user));
 }

 // Admin-only: list all users
 @GetMapping
 @PreAuthorize("hasRole('ADMIN')")
 public ResponseEntity<List<UserResponse>> listAll() {
     return ResponseEntity.ok(userService.listAll());
 }
}
