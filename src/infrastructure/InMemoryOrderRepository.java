package infrastructure;

import domain.model.Order;
import domain.repository.OrderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<Integer, OrderEntity> orders = new HashMap<>();

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderEntityMapper.toEntity(order);
        return OrderEntityMapper.toDomain(orders.put(order.getId(), entity));
    }

    @Override
    public Optional<Order> findById(int id) {
        return Optional.ofNullable(orders.get(id)).map(OrderEntityMapper::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return orders.values().stream().map(OrderEntityMapper::toDomain).toList();
    }
}
