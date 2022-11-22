package edu.miu.order.service.impl;

import edu.miu.order.helper.Mapper;
import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.model.entity.Order;
import edu.miu.order.model.entity.OrderStatus;
import edu.miu.order.repository.OrderRepository;
import edu.miu.order.service.OrderService;
import edu.miu.order.service.kafka.OrderStream;
import edu.miu.order.service.kafka.OrderTopicProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OwnerOrderService implements OrderService {
    private final OrderTopicProducer topicProducer;

    @Value("${topic.orderDeclined.name}")
    private String orderDeclinedTopicName;

    @Value("${topic.orderApproved.name}")
    private String orderApprovedTopicName;

    final private Mapper mapper;
    final private OrderRepository orderRepository;
    @Override
    public List<OrderResponse> showOrders(String ownerId) {
        List<Order> orders =orderRepository.findByOwnerId(ownerId);
        return mapper.mapResponseList(orders, new OrderResponse());
    }

    @Override
    public OrderResponse getOrderById(String id) {
        return new OrderResponse();
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {
    // TODO: 11/22/22 refactor interface and remove
    }

    @Override
    public void updateByID(String id, Order orderRequest) {
    // TODO: 11/22/22 refactor and remove
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    public void declineOrder(String id) {
        Order order = orderRepository.findById(id).get();
        order.setOrderStatus(OrderStatus.DECLINED);
        orderRepository.save(order);
        OrderStream orderStream = new OrderStream();
        orderStream.setId("orderRequest.id");
        orderStream.setPrice(order.getPrice());
        orderStream.setStartDate(order.getStartDate());
        orderStream.setEndDate(order.getEndDate());
        orderStream.setProperty(order.getProperty());
        topicProducer.send(orderDeclinedTopicName,orderStream);
    }
    public void approveOrder(String id) {
        Order order = orderRepository.findById(id).get();
        order.setOrderStatus(OrderStatus.APPROVED);
        OrderStream orderStream = new OrderStream();
        orderStream.setId("orderRequest.id");
        orderStream.setPrice(order.getPrice());
        orderStream.setStartDate(order.getStartDate());
        orderStream.setEndDate(order.getEndDate());
        orderStream.setProperty(order.getProperty());
        topicProducer.send(orderApprovedTopicName,orderStream);
    }
}
