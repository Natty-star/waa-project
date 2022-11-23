package edu.miu.order.model.DTO.response;

import edu.miu.order.model.entity.Customer;
import edu.miu.order.model.entity.OrderStatus;
import edu.miu.order.model.entity.Owner;
import edu.miu.order.model.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse   {


    private Customer customer;
    private Property property;
    private Owner owner;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private OrderStatus orderStatus;

}
