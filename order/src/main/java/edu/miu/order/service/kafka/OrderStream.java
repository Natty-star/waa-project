package edu.miu.order.service.kafka;

import edu.miu.order.model.entity.Customer;
import edu.miu.order.model.entity.OrderStatus;
import edu.miu.order.model.entity.Owner;
import edu.miu.order.model.entity.Property;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderStream {


    private Customer customer;
    private Property property;
    private Owner owner;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;



}
