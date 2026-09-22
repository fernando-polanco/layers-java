package infraestructure;

import domain.model.OrderItem;
import domain.model.OrderStatus;

import java.util.List;

public class OrderEntity {
    private int id;
    private String customerName;
    private List<OrderItem> products;
    private OrderStatus status;

    public OrderEntity(int id, String customerName, List<OrderItem> products, OrderStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.products = products;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public List<OrderItem> getProducts() {
        return products;
    }
    public OrderStatus getStatus() {
        return status;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
