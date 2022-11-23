package edu.miu.order.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private String propertyId;
    private String propertyType;
    private String propertyTitle;
}
