package com.cts.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.cts.model.Account;
 
public interface AccountRepository extends JpaRepository<Account, Long> {
}