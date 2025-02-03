package com.msdemo.order_service.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryStockResponse {
    private String skuCode;
    private boolean isInStock;

    public InventoryStockResponse() {
    }

    public InventoryStockResponse(String skuCode, boolean isInStock) {
        this.skuCode = skuCode;
        this.isInStock = isInStock;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
