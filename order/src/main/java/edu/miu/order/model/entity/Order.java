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
    String id;
    String user;
    String property;
    String owner;
    double price;
    LocalDate startDate;

    LocalDate endDate;
    OrderStatus orderStatus;






}
