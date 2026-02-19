package com.cts.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cts.dto.RegisterRequest;
import com.cts.dto.UserResponse;
import com.cts.model.User;
import com.cts.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

 private final UserRepository repo;

 @Transactional
 public UserResponse register(RegisterRequest req) {
     if (repo.existsByEmail(req.getEmail().toLowerCase())) {
         throw new IllegalArgumentException("Email already registered");
     }
     User user = User.builder()
             .name(req.getName())
             .role(req.getRole())
             .email(req.getEmail().toLowerCase())
             .phone(req.getPhone())
             .build();
     return toResponse(repo.save(user));
 }

 public UserResponse toResponse(User u) {
     return new UserResponse(u.getId(), u.getName(), u.getRole(), u.getEmail(), u.getPhone());
 }

 public List<UserResponse> listAll() {
     return repo.findAll().stream().map(this::toResponse).toList();
 }
}
