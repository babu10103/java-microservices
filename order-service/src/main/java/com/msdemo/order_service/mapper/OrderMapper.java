package com.msdemo.order_service.mapper;

import com.msdemo.order_service.model.Order;
import com.msdemo.order_service.model.OrderLineItems;
import com.msdemo.order_service.model.OrderLineItemsDto;
import com.msdemo.order_service.model.OrderRequest;
import com.msdemo.order_service.model.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    /**
     * Maps an OrderRequest DTO to an Order entity.
     */
    public Order mapToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToOrderLineItems)
                .collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItemsList);
        return order;
    }

    /**
     * Maps an OrderLineItemsDto to an OrderLineItems entity.
     */
    public OrderLineItems mapToOrderLineItems(OrderLineItemsDto dto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(dto.getSkuCode());
        orderLineItems.setPrice(dto.getPrice());
        orderLineItems.setQuantity(dto.getQuantity());
        return orderLineItems;
    }

    /**
     * Maps an Order entity to an OrderResponse DTO.
     */
    public OrderResponse mapToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setOrderNumber(order.getOrderNumber());
        List<OrderLineItemsDto> orderLineItemsDtoList = order.getOrderLineItemsList()
                .stream()
                .map(this::mapToOrderLineItemsDto)
                .collect(Collectors.toList());
        response.setOrderLineItemsDtoList(orderLineItemsDtoList);
        return response;
    }

    /**
     * Maps an OrderLineItems entity to an OrderLineItemsDto.
     */
    public OrderLineItemsDto mapToOrderLineItemsDto(OrderLineItems orderLineItems) {
        OrderLineItemsDto dto = new OrderLineItemsDto();
        dto.setId(orderLineItems.getId());
        dto.setSkuCode(orderLineItems.getSkuCode());
        dto.setPrice(orderLineItems.getPrice());
        dto.setQuantity(orderLineItems.getQuantity());
        return dto;
    }
}
