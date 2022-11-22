package edu.miu.order.repository;

import edu.miu.order.model.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    @Query("{user:'?0'}")
    List<Order> findByUserId(String userId);
    @Query("{owner:'?0'}")
    List<Order> findByOwnerId(String ownerId);
}
