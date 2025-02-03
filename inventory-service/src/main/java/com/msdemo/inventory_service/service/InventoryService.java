package com.msdemo.inventory_service.service;

import com.msdemo.inventory_service.mapper.InventoryMapper;
import com.msdemo.inventory_service.model.Inventory;
import com.msdemo.inventory_service.model.InventoryRequest;
import com.msdemo.inventory_service.model.InventoryResponse;
import com.msdemo.inventory_service.model.InventoryStockResponse;
import com.msdemo.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
    }

    public void addInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryMapper.mapInventoryRequestToInventory(inventoryRequest);
        inventoryRepository.save(inventory);
    }

    public List<InventoryResponse> getInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return this.inventoryMapper.mapInventoryToInventoryResponseList(inventoryList);
    }

    @Transactional(readOnly = true)
    public List<InventoryStockResponse> isInStock(List<String> skuCodes) {
        List<Inventory> inventoryList = inventoryRepository.findAllBySkuCodeIn(skuCodes);
        return inventoryMapper.mapInventoryToInventoryStockResponseList(inventoryList);
    }

}
