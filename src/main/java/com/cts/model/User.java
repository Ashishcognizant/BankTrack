package com.cts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
     @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 
	 @NotBlank(message = "Name is required")
	 private String name;

	 // Role (Customer/Officer/Admin)
	 @Enumerated(EnumType.STRING)
	 @Column(nullable = false, length = 20)
	 private Role role;

	 

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;


     @NotBlank(message = "Phone is required")
     @Size(max = 20, message = "Phone must be at most 20 characters")
     private String phone;
}





