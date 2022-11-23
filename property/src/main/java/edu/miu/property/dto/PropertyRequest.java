package edu.miu.property.dto;

import edu.miu.property.model.Category;
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
    private String type;
    private int bedrooms;
    private String description;
    private String ownerEmail;
    private double price;
    private Boolean status;
}
