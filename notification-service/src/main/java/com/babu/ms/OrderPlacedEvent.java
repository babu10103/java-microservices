package com.babu.ms;

public class OrderPlacedEvent {
    private String orderNumber;

    public OrderPlacedEvent() {
    }

    public OrderPlacedEvent(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
