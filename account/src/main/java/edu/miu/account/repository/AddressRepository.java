package edu.miu.account.repository;

import edu.miu.account.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address,String> {
}
