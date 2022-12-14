package edu.miu.account.service.Imp;

import edu.miu.account.dto.AuthResponse;
import edu.miu.account.dto.ChangePassword;
import edu.miu.account.entity.Account;
import edu.miu.account.repository.AccountRepository;
import edu.miu.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImp implements AccountService {
    PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImp(){
        passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public List<Account> findAllUsers() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(String email, String password) {
        Account account = accountRepository.findAccountByEmail(email);
        return passwordEncoder.matches(password,account.getPassword())? account : null;
    }

    @Override
    public AuthResponse getAuthResponse(Account account) {
        return new AuthResponse(true, account.getFirstName(), account.getLastName(), account.getEmail(), account.getRoles());
    }

    @Override
    public Account findUserById(String id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account createAccount(Account account) {
        try {
            String encodedPassword = passwordEncoder.encode(account.getPassword());
            account.setPassword(encodedPassword);
            if(account.getRoles().get(0).getName().equals("owner")){
                account.setIsActive(false);
            }else {
                account.setIsActive(true);
            }

            return accountRepository.save(account);
        }catch (Exception e){
            log.error("User already exists with this email address: " + LocalDateTime.now());
            throw new RuntimeException("User already exists with this email address: " + account.getEmail());
        }
    }

    @Override
    public Account updateAccount(String email, Account account) {
        var getAccount = accountRepository.findAccountByEmail(email);
        getAccount.setFirstName(account.getFirstName());
        getAccount.setLastName(account.getLastName());
        getAccount.setAddress(account.getAddress());
        return accountRepository.save(getAccount);
    }

    @Override
    public Account disAbleAccount(String id) {
        var getAccount = accountRepository.findById(id).get();
        getAccount.setIsActive(!getAccount.getIsActive());
        return accountRepository.save(getAccount);
    }

    @Override
    public List<Account> getDeactivatedOwner() {
        List<Account> accountList = accountRepository.findAll();
        List<Account> accounts = accountList.stream().filter(account -> account.getRoles().get(0).getName().equals("owner"))
                .filter(account -> account.getIsActive().equals(false)).collect(Collectors.toList());

        return accounts;
    }

    @Override
    public Account changePassword(ChangePassword changePassword) {
        try {
            Account account = accountRepository.findAccountByEmail(changePassword.getEmail());
            String encodedPassword = passwordEncoder.encode(changePassword.getPassword());
            account.setPassword(encodedPassword);
            return account;

        }catch (Exception e){
            log.error("User email not exists with this email address: " + LocalDateTime.now());
            throw new RuntimeException("User not exists with this email address: " + changePassword.getEmail());
        }
    }

}
