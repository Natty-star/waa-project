package edu.miu.property.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
public class Property {
    @Id
    private String id;
    private Category category;
    private String type;
    private int bedrooms;
    private String title;
    private String description;
    private double price;
    private Boolean status;
    private String ownerEmail;
    private List<String> images;
    private Double[] location;
}
