package application.dto;

import java.util.List;

public record CreateOrderRequest(String customerName, List<String> products) {
}
