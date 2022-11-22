package edu.miu.account.repository;

import edu.miu.account.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account,String> {
    Account findAccountByEmail(String email);
}
