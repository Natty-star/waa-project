package edu.miu.order.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    private String ownerId;
    private String ownerName;
    private String ownerEmail;
}
