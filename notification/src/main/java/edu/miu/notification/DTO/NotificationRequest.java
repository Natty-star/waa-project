package edu.miu.notification.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String customerEmail;
    private String ownerName;
    private String customerName;
    private String hostUserEmail;
    private String propertyType;
    private String propertyTitle;
    private String startDate;
    private String endDate;
}
