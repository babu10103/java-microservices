package com.msdemo.inventory_service.model;

import lombok.Builder;

@Builder
public class InventoryResponse {
    private Long id;
    private String skuCode;
    private Long quantity;

    public InventoryResponse(Long id, String skuCode, Long quantity) {
        this.id = id;
        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    public InventoryResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
