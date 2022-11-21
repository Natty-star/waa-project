package edu.miu.order.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document()
@Data
public class Order {
    @Id
    String id;

    String userId;

    String propertyId;

    double price;

    LocalDate startDate;

    LocalDate endDate;






}
