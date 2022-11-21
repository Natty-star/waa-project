package edu.miu.order.service;


import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {


    public List<OrderResponse> showOrders();
    public OrderResponse getOrderById(String id);
    public void saveOrder(OrderRequest orderRequest);
    public void updateByID(String id,OrderRequest orderRequest);
    public void deleteOrder(String id);


}
