package edu.miu.account.service;

import edu.miu.account.dto.AuthResponse;
import edu.miu.account.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    List<Account> findAllUsers();

    Account getAccount(String username, String password);

    AuthResponse getAuthResponse(Account account);

    Account findUserById(String id);

    Account createAccount(Account account);


    Account updateAccount(String email, Account account);

    Account disAbleAccount(String id);
}
