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

@RequiredArgsConstructor
@Service
public class CustomerOrderService implements OrderService {
    final private Mapper mapper;
    final private OrderRepository orderRepository;


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
