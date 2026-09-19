package domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private final String id;
    private String customerName;
    private List<OrderItem> products;
    private OrderStatus status;

    private static final BigDecimal DISCOUNT_THRESHOLD = new BigDecimal("1000");
    private static final BigDecimal DISCOUNT_FACTOR = new BigDecimal("0.90");
    private static final BigDecimal TAX_FACTOR = new BigDecimal("1.16");

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

    public BigDecimal total() {
        if (products == null || products.isEmpty()) {
            throw new IllegalStateException("No se puede calcular el total de un pedido vacío");
        }

        return subtotal().compareTo(DISCOUNT_THRESHOLD) >= 0 ?
                subtotal().multiply(DISCOUNT_FACTOR).multiply(TAX_FACTOR) :
                subtotal().multiply(TAX_FACTOR);
    }
}
