package edu.miu.order.service.impl;

import edu.miu.order.helper.Mapper;
import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.model.entity.Order;
import edu.miu.order.repository.OrderRepository;
import edu.miu.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AdminOrderService implements OrderService {
    final private Mapper mapper;
    final private OrderRepository orderRepository;

    @Override
    public List<OrderResponse> showOrders(String userId) {
        List<Order> orders = orderRepository.findAll();
        return mapper.mapResponseList(orders, new OrderResponse());
    }

    @Override
    public OrderResponse getOrderById(String orderId) {
        Order order =  orderRepository.findById(orderId).get();
        return mapper.mapToResponse(order, new OrderResponse());
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {

    }

    @Override
    public void updateByID(String id, Order orderRequest) {

    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
