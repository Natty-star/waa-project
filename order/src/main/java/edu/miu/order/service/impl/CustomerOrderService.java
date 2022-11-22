package edu.miu.order.service.impl;

import edu.miu.order.helper.Mapper;
import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.model.entity.Order;
import edu.miu.order.repository.OrderRepository;
import edu.miu.order.service.OrderService;
import edu.miu.order.service.kafka.OrderStream;
import edu.miu.order.service.kafka.OrderTopicProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerOrderService implements OrderService {
    final private Mapper mapper;
    final private OrderRepository orderRepository;

    private final OrderTopicProducer topicProducer;



    @Override
    public List<OrderResponse> showOrders() {
        List<Order> orders = orderRepository.findAll();
        return mapper.mapResponseList(orders, new OrderResponse());
    }

    @Override
    public OrderResponse getOrderById(String id) {
        Order order =  orderRepository.findById(id).get();
        return mapper.mapToResponse(order, new OrderResponse());

    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {
            Order order = mapper.mapToOrder(orderRequest,new Order());
            orderRepository.save(order);
        OrderStream orderStream = new OrderStream();
        orderStream.setId("orderRequest.id");
        orderStream.setPrice(orderRequest.getPrice());
        orderStream.setStartDate(orderRequest.getStartDate());
        orderStream.setEndDate(orderRequest.getEndDate());
        orderStream.setProperty(orderRequest.getProperty());
        topicProducer.send(orderStream);

    }

    @Override
    public void updateByID(String id, Order orderRequest) {
        Order orderTobeUpdated = orderRepository.findById(id).get();
        orderRequest.setId(orderTobeUpdated.getId());
        orderTobeUpdated  = orderRequest;
        orderRepository.save(orderTobeUpdated);

    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
