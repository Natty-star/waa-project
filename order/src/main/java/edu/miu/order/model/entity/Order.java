package edu.miu.order.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document()
@Data
//@NoArgsConstructor
public class Order {

    @Id
    private String id;
    private Customer customer;
    private Property property;
    private Owner owner;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private OrderStatus orderStatus;







}
