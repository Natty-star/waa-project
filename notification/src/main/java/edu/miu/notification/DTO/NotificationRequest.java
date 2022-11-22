package edu.miu.notification.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String gustUserEmail;
    private String hostUserEmail;
    private String propertyName;
    private String propertyTitle;
    private String startDate;
    private String endDate;
}
