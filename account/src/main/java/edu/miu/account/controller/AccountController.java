package edu.miu.account.controller;

import edu.miu.account.entity.Account;
import edu.miu.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<?> findAllUsers(){
        var account = accountService.findAllUsers();
        return ResponseEntity.ok().body(account);
    }
}
