package edu.miu.order.service.impl;

import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.model.entity.Order;
import edu.miu.order.service.OrderService;

import java.util.List;

public class OwnerOrderService implements OrderService {
    @Override
    public List<OrderResponse> showOrders() {
        return null;
    }

    @Override
    public OrderResponse getOrderById(String id) {
//        return
        return new OrderResponse();
//        return Optional.empty();
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {

    }

    @Override
    public void updateByID(String id, Order orderRequest) {

    }

    @Override
    public void deleteOrder(String id) {

    }
}
