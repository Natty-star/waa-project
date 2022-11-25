package edu.miu.account.repository;

import edu.miu.account.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account,String> {
    Account findAccountByEmail(String email);

}
