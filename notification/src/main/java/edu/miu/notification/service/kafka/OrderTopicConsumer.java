package edu.miu.notification.service.kafka;

import edu.miu.notification.DTO.NotificationRequest;
import edu.miu.notification.service.EmailTemplate;
import edu.miu.notification.service.GmailService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderTopicConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${topic.orderCreated.name}")
    private String orderCreatedTopic;

    @Value("$topic.orderApproved.name")
    private String orderApprovedTopic;

    @Value("$topic.orderDeclined.name")
    private String orderDeclined;

    @Autowired
    GmailService gmailService;

    @KafkaListener(topics = "${topic.orderCreated.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, OrderStream> payload){
        logger.warn("Consumed Order", payload);
        OrderStream orderStream  = payload.value();
        logger.warn(orderCreatedTopic, payload);
        NotificationRequest req =
                new NotificationRequest(
                        orderStream.getCustomer().getCustomerEmail(),
                        orderStream.getOwner().getOwnerName(),
                        orderStream.getCustomer().getCustomerName(),
                        "waa.test.pro@gmail.com",
                        orderStream.getProperty().getPropertyType(),
                        orderStream.getProperty().getPropertyTitle() ,
                        orderStream.getStartDate().toString(),
                        orderStream.getEndDate().toString()
                );
        gmailService.sendHtmlEmail(
                orderStream.getOwner().getOwnerEmail(),
                "New Order Placed",
                new EmailTemplate().orderCreatedEmailTemplate(req));

    }

    @KafkaListener(topics = "${topic.orderCanceled.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderCanceled(ConsumerRecord<String,OrderStream> payload){
        logger.warn("Consumed Order Delete" , payload);
        logger.warn(orderCreatedTopic, payload);
        OrderStream orderStream  = payload.value();
        NotificationRequest request =
                new NotificationRequest(
                        orderStream.getCustomer().getCustomerEmail(),
                        orderStream.getOwner().getOwnerName(),
                        orderStream.getCustomer().getCustomerName(),
                        "waa.test.pro@gmail.com",
                        orderStream.getProperty().getPropertyType(),
                        orderStream.getProperty().getPropertyTitle() ,
                        orderStream.getStartDate().toString(),
                        orderStream.getEndDate().toString()

                );
        gmailService.sendHtmlEmail(
                orderStream.getOwner().getOwnerEmail(),
                "Order Canceled",
                new EmailTemplate().orderCanceledEmailTemplate(request)
        );

    }
    @KafkaListener(topics = "${topic.orderApproved.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderApproved(ConsumerRecord<String,OrderStream> payload){
        logger.warn("Consumed Order Approved" , payload);
        OrderStream orderStream  = payload.value();
        NotificationRequest request =
                new NotificationRequest(
                        orderStream.getCustomer().getCustomerEmail(),
                        orderStream.getOwner().getOwnerName(),
                        orderStream.getCustomer().getCustomerName(),
                        "waa.test.pro@gmail.com",
                        orderStream.getProperty().getPropertyType(),
                        orderStream.getProperty().getPropertyTitle() ,
                        orderStream.getStartDate().toString(),
                        orderStream.getEndDate().toString()

                );
        gmailService.sendHtmlEmail(
                orderStream.getCustomer().getCustomerEmail(),
                "Order Approved",
                new EmailTemplate().orderApprovedEmailTemplate(request)
        );

    }



    @KafkaListener(topics = "${topic.orderDeclined.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderDeclined(ConsumerRecord<String,OrderStream> payload){
        OrderStream orderStream  = payload.value();
        NotificationRequest request =
                new NotificationRequest(
                        orderStream.getCustomer().getCustomerEmail(),
                        orderStream.getOwner().getOwnerName(),
                        orderStream.getCustomer().getCustomerName(),
                        "waa.test.pro@gmail.com",
                        orderStream.getProperty().getPropertyType(),
                        orderStream.getProperty().getPropertyTitle() ,
                        orderStream.getStartDate().toString(),
                        orderStream.getEndDate().toString()
                );
        gmailService.sendHtmlEmail(
                orderStream.getCustomer().getCustomerEmail(),
                "Order Declined!",
                new EmailTemplate().orderDeclinedEmailTemplate(request)
        );

    }


}
