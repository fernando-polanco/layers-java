package infraestructure.persistence;

import domain.model.Order;

public class OrderEntityMapper {
    public static OrderEntity toEntity(Order order) {
        return new OrderEntity(order.getId(), order.getCustomerName(), order.getProducts(), order.getStatus());
    }

    public static Order toDomain(OrderEntity orderEntity) {
        return new Order(orderEntity.getId(), orderEntity.getCustomerName(), orderEntity.getProducts(), orderEntity.getStatus());
    }
}
