package com.msdemo.inventory_service.controller;

import com.msdemo.inventory_service.model.InventoryRequest;
import com.msdemo.inventory_service.model.InventoryResponse;
import com.msdemo.inventory_service.model.InventoryStockResponse;
import com.msdemo.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<InventoryResponse> getInventory() {
//        return inventoryService.getInventory();
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.addInventory(inventoryRequest);
        return "Added Item to inventory.";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryStockResponse> isInStock(@RequestParam("skuCode") List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
