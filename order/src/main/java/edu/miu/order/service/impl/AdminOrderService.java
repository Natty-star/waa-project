package edu.miu.order.service.impl;

import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.service.OrderService;

import java.util.List;
import java.util.Optional;

public class AdminOrderService implements OrderService {
    @Override
    public List<OrderResponse> showOrders() {
        return null;
    }

    @Override
    public OrderResponse getOrderById(String orderId) {
        return new OrderResponse();
//        return Optional.empty();
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {

    }

    @Override
    public void updateByID(String id, OrderRequest orderRequest) {

    }

    @Override
    public void deleteOrder(String id) {

    }
}
