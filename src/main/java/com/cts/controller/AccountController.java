package com.cts.controller;
 
import java.util.List;
 
import javax.security.auth.login.AccountNotFoundException;
 
import org.springframework.web.bind.annotation.*;
 
import com.cts.model.Account;
import com.cts.service.AccountService;
 
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
 
    private final AccountService accountService;
 
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
 
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
 
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) throws AccountNotFoundException {
        return accountService.getAccount(id); // throws RuntimeException if not found
    }
 
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
 
    @PutMapping("/{id}/status")
    public Account updateStatus(@PathVariable Long id, @RequestParam String status) throws AccountNotFoundException {
        return accountService.updateAccountStatus(id, status);
    }
 
    @GetMapping("/{id}/balance")
    public Double getBalance(@PathVariable Long id) throws AccountNotFoundException {
        return accountService.getBalance(id);
    }
}