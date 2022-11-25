package edu.miu.property.dto;

import edu.miu.property.model.Category;
import edu.miu.property.model.HomeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyRequest {
    private String title;
    private Category category;
    private int bedrooms;
    private int bathrooms;
    private String description;
    private String ownerEmail;
    private double price;
    private Boolean status;
    private double area;
    private HomeType homeType;
}
