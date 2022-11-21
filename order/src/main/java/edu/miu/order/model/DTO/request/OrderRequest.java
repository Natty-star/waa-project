package edu.miu.order.model.DTO.request;

import java.time.LocalDate;

public class OrderRequest {

    String userId;

    String propertyId;

    double price;

    LocalDate startDate;

    LocalDate endDate;
}
