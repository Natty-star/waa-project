package edu.miu.notification.service.kafka;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderStream {
    String id;

    String user;

    String property;

    double price;

    LocalDate startDate;

    LocalDate endDate;

}