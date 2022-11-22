package edu.miu.order.controller;

import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.model.entity.Order;
import edu.miu.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
   final private OrderService orderService;


    @GetMapping
    public List<OrderResponse> getOrders(){
        return orderService.showOrders();

    }
    @PostMapping
    public void saveOrder(@RequestBody OrderRequest orderRequest){
        orderService.saveOrder(orderRequest);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable String id){
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable String id,@RequestBody Order orderRequest){
        orderService.updateByID(id,orderRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id){
         orderService.deleteOrder(id);
    }





}
