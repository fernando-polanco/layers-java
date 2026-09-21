package domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private final int id;
    private final String customerName;
    private final List<OrderItem> products;
    private OrderStatus status;

    public Order(int id, String customerName, List<OrderItem> products, OrderStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.products = products;
        this.status = status;
    }

    public static Order create(int id, String customerName, List<OrderItem> products) {
        return new Order(id, customerName, products, OrderStatus.PENDING);
    }

    public BigDecimal subtotal() {
        if (products == null || products.isEmpty()) {
            throw new IllegalStateException("No se puede calcular el subtotal de un pedido vacío");
        }

        return products.stream()
                .map(OrderItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getId() {
        return id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public List<OrderItem> getProducts() {
        return List.copyOf(products);
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public OrderStatus getStatus() {
        return status;
    }
}
