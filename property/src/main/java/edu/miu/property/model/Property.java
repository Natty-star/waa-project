package edu.miu.property.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivateKey;
import java.util.List;
import javax.persistence.*;

@Document
@Data
@Builder
public class Property {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private HomeType homeType;

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
