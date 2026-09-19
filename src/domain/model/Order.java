package domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private final String id;
    private final String customerName;
    private final List<OrderItem> products;
    private OrderStatus status;

    public Order(String id, String customerName, List<OrderItem> products) {
        this.id = id;
        this.customerName = customerName;
        this.products = products;
        this.status = OrderStatus.PENDING;
    }

    public BigDecimal subtotal() {
        if (products == null || products.isEmpty()) {
            throw new IllegalStateException("No se puede calcular el subtotal de un pedido vacío");
        }

        return products.stream()
                .map(OrderItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
