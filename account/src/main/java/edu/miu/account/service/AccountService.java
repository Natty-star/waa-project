package edu.miu.account.service;

import edu.miu.account.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    List<Account> findAllUsers();
}
