package edu.miu.notification.DTO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class LogObject {
    String status;
    String email;
    String reservationId;
    String paymentType;
}
