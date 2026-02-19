package com.cts.model;
 
import jakarta.persistence.*;
import lombok.*;
 
@Entity
@Table(name = "Account")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;
 
    private Long customerID;
 
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
 
    private Double balance;
 
    private String status;
}