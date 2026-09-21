package application.dto;

import domain.model.OrderItem;
import domain.model.OrderStatus;

import java.util.List;

public record OrderDTO(int id, String customerName, List<OrderItem> products, OrderStatus status) {
    @Override
    public String toString() {
        return "OrderDTO: {" + System.lineSeparator() +
                "  id: " + id + System.lineSeparator() +
                "  customerName: " + customerName + System.lineSeparator() +
                "  products: " + products + System.lineSeparator() +
                "  status: " + status + System.lineSeparator() +
                "}";
    }
}
