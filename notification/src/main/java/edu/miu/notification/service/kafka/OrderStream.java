package edu.miu.notification.service.kafka;

import edu.miu.notification.DTO.Customer;
import edu.miu.notification.DTO.OrderStatus;
import edu.miu.notification.DTO.Owner;
import edu.miu.notification.DTO.Property;
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
    private OrderStatus orderStatus;


}