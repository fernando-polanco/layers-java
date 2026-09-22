package application.dto;

import domain.model.OrderItem;

import java.util.List;

public record CreateOrderRequest(String customerName, List<OrderItem> products) {
}
