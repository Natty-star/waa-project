package edu.miu.property.kafka;


import edu.miu.property.dto.Customer;
import edu.miu.property.dto.OrderStatus;
import edu.miu.property.dto.Owner;
import edu.miu.property.dto.Property;
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
