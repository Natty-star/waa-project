package edu.miu.order.model.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest  {

    String user;

    String property;

    double price;

    LocalDate startDate;

    LocalDate endDate;
}
