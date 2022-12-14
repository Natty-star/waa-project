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
public class UpdateDto {
    private String id;
    private Category category;
//    private String type;
    private int bedrooms;
    private String title;
    private String description;
    private double price;
    private Boolean status;
    private String ownerEmail;

}
