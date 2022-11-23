package edu.miu.notification.DTO;

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
