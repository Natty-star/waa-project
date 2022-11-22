package edu.miu.account.service.Imp;

import edu.miu.account.entity.Account;
import edu.miu.account.repository.AccountRepository;
import edu.miu.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> findAllUsers() {
        return accountRepository.findAll();
    }
}
