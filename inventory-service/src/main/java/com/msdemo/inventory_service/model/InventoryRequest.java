package com.msdemo.inventory_service.model;

import jakarta.persistence.Id;

public class InventoryRequest {
    private String skuCode;
    private Long quantity;

    public InventoryRequest(String skuCode, Long quantity) {
        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    public InventoryRequest() {
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
