package com.cts.service;
 
import java.util.List;
 
import javax.security.auth.login.AccountNotFoundException;
 
import org.springframework.stereotype.Service;
 
import com.cts.model.Account;
import com.cts.repository.AccountRepository;
 
import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor // Lombok generates constructor for final fields
public class AccountService {
 
    private final AccountRepository accountRepository;
 
    // Create Account
    public Account createAccount(Account account) {
        account.setStatus("Active");
        return accountRepository.save(account);
    }
 
    // Get Account by ID
    public Account getAccount(Long id) throws AccountNotFoundException {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }
 
    // Get All Accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
 
    // Update Account Status
    public Account updateAccountStatus(Long id, String status) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setStatus(status);
        return accountRepository.save(account);
    }
 
    // Track Balance
    public Double getBalance(Long id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account.getBalance();
    }
}