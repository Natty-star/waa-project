package edu.miu.account.controller;

import edu.miu.account.dto.AuthRequest;
import edu.miu.account.dto.AuthResponse;
import edu.miu.account.entity.Account;
import edu.miu.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) throws Exception{
        Account account = accountService.getAccount(authRequest.getUsername(), authRequest.getPassword());
        AuthResponse authResponse = account == null ? new AuthResponse() : accountService.getAuthResponse(account);
        authResponse.setStatus(account != null);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers(){
        var account = accountService.findAllUsers();
        return ResponseEntity.ok().body(account);
    }

    @GetMapping("/di-active/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        Account account = accountService.findUserById(id);
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createAccount(@RequestBody Account account){
        Account newAccount = accountService.createAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateAccount(@PathVariable("email") String email, @RequestBody Account account){
        Account updatedAccount = accountService.updateAccount(email,account);
        return ResponseEntity.ok(updatedAccount);
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<?> disAbleAccount(@PathVariable("id") String id){
        Account disAbleAccount = accountService.disAbleAccount(id);
        return ResponseEntity.ok(disAbleAccount);
     }
}
