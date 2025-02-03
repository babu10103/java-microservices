package com.msdemo.order_service.service;

import com.msdemo.order_service.config.WebClientFactory;
import com.msdemo.order_service.mapper.OrderMapper;
import com.msdemo.order_service.model.*;
import com.msdemo.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient webClient;

    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        WebClientFactory webClientFactory,
                        @Value("${external.apis.inventory-service.url}") String baseUrl) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.webClient = webClientFactory.createWebClient(baseUrl);
    }

    public void placeOrder(OrderRequest orderRequest) {
        // Use the mapper to convert OrderRequest to an Order entity.
        Order order = orderMapper.mapToOrder(orderRequest);

        // Extract SKU codes from order line items.
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        // Ensure skuCodes is not empty before making the request.
        if (skuCodes.isEmpty()) {
            throw new IllegalArgumentException("No SKU codes provided in the order.");
        }

        // Make the inventory stock service call using WebClient to check stock for the given SKUs.
        InventoryStockResponse[] res = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", String.join(",", skuCodes))
                        .build())
                .retrieve()
                .bodyToMono(InventoryStockResponse[].class)
                .block();
        if (res == null || res.length == 0) {
            throw new RuntimeException("Inventory check failed or no inventory data received.");
        }

        // Save the order in the repository
        orderRepository.save(order);
    }


    public List<OrderResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();
        // Map each Order entity to an OrderResponse DTO.
        return orders.stream()
                .map(orderMapper::mapToOrderResponse)
                .collect(Collectors.toList());
    }

}
