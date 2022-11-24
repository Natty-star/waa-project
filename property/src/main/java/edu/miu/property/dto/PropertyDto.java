package edu.miu.property.dto;

import edu.miu.property.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDto {
    private Category category;
    private String type;
    private int bedrooms;
    private int bathrooms;
    private double area;
    private String title;
    private String description;
    private double price;
    private Boolean status;
    private String ownerEmail;
    private List<String> images;
    private Double[] location;
}
