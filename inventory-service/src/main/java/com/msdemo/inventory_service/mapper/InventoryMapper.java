package com.msdemo.inventory_service.mapper;

import com.msdemo.inventory_service.model.Inventory;
import com.msdemo.inventory_service.model.InventoryRequest;
import com.msdemo.inventory_service.model.InventoryResponse;
import com.msdemo.inventory_service.model.InventoryStockResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryMapper {
    public Inventory mapInventoryRequestToInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        return inventory;
    }
    public InventoryResponse mapInventoryToInventoryResponse(Inventory inventory) {
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setId(inventory.getId());
        inventoryResponse.setQuantity(inventory.getQuantity());
        inventoryResponse.setSkuCode(inventory.getSkuCode());
        return inventoryResponse;
    }
    public List<InventoryResponse> mapInventoryToInventoryResponseList(List<Inventory> inventoryList) {
        return inventoryList
                .stream()
                .map(this::mapInventoryToInventoryResponse)
                .collect(Collectors.toList());
    }
    public List<InventoryStockResponse> mapInventoryToInventoryStockResponseList(List<Inventory> inventoryList) {
        return inventoryList
                .stream()
                .map(inventory ->
                          new InventoryStockResponse(inventory.getSkuCode(), inventory.getQuantity() > 0))
                .collect(Collectors.toList());
    }
}
