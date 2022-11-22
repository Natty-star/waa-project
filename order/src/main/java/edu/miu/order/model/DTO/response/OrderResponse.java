package edu.miu.order.model.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse   {

    String id;
    String user;

    String property;

    double price;

    LocalDate startDate;

    LocalDate endDate;
}
