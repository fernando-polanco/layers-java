package application.mapper;

import application.dto.OrderDTO;
import domain.model.Order;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderMapper {
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(order.getId(), order.getCustomerName(), order.getProducts(), order.getStatus());
    }

    public static Order toDomain(OrderDTO orderDTO) {
        return new Order(orderDTO.id(), orderDTO.customerName(), orderDTO.products(), orderDTO.status());
    }

    public static int generateId() {
        return counter.incrementAndGet();
    }
}
