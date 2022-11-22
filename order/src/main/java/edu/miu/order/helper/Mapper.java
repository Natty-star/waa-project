package edu.miu.order.helper;

import edu.miu.order.model.DTO.request.OrderRequest;
import edu.miu.order.model.DTO.response.OrderResponse;
import edu.miu.order.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class Mapper   {


   private final ModelMapper modelMapper;

    /***
     *
     *
     * @Author - Dr. Muhyieddin
     *
     * We took this helper function from the demo code
     * because I like the way u structured it.
     */
    public List<OrderResponse> mapResponseList(List<Order> list, OrderResponse convertTo){
        return
                list.stream()
                        .map(e -> modelMapper.map(e,convertTo.getClass()))
                        .collect(Collectors.toList());
    }
    public List<OrderRequest> mapRequestList(List<Order> list, OrderRequest convertTo){
        return
                list.stream()
                        .map(e -> modelMapper.map(e,convertTo.getClass()))
                        .collect(Collectors.toList());
    }

    public OrderRequest mapToRequest(Order source, OrderRequest convertTo){
        return (OrderRequest) modelMapper.map(source,convertTo.getClass());
    }
    public Order mapToOrder(OrderRequest source, Order convertTo){
        return (Order) modelMapper.map(source,convertTo.getClass());
    }

    public OrderResponse mapToResponse(Order source, OrderResponse convertTo){
        return (OrderResponse) modelMapper.map(source,convertTo.getClass());
    }
}
