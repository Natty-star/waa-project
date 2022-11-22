package edu.miu.order.service;


import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface OrderService {
    public List<OrderResponse> showOrders(String userId);
    public OrderResponse getOrderById(String id);
    public void saveOrder(OrderRequest orderRequest);
    public void updateByID(String id,Order orderRequest);
    public void deleteOrder(String id);


}
