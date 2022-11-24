package edu.miu.property.kafka;



import edu.miu.property.dto.*;
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
