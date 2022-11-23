package edu.miu.order.controller;

import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.model.entity.Order;
import edu.miu.order.service.OrderService;
import edu.miu.order.service.impl.AdminOrderService;
import edu.miu.order.service.impl.CustomerOrderService;
import edu.miu.order.service.impl.OwnerOrderService;
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
   final private CustomerOrderService customerOrderService;

    @Autowired
    final private OwnerOrderService ownerOrderService;

    @Autowired
    final private AdminOrderService adminOrderService;


    @GetMapping("/{userId}")
    public List<OrderResponse> getOrders(@PathVariable String userId, @RequestBody String role ){

      return  role == "ADMIN"
              ? adminOrderService.showOrders(userId)
              :role=="OWNER"
              ? ownerOrderService.showOrders(userId)
              :customerOrderService.showOrders(userId);

    }
    @PostMapping
    public void saveOrder(@RequestBody OrderRequest orderRequest){
        customerOrderService.saveOrder(orderRequest);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable String id){
        return customerOrderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable String id,@RequestBody Order orderRequest){
        customerOrderService.updateByID(id,orderRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id){
        customerOrderService.deleteOrder(id);
    }

    @PutMapping("/{id}/approve")
    public void approveOrder(@PathVariable String id){
        ownerOrderService.approveOrder(id);
    }


    @PutMapping("/{id}/decline")
    public void declineOrder(@PathVariable String id){
        ownerOrderService.declineOrder(id);

    }





}
