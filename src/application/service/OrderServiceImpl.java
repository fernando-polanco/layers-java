package application.service;

import application.dto.CreateOrderRequest;
import application.dto.OrderDTO;
import application.mapper.OrderMapper;
import domain.exception.OrderNotFoundException;
import domain.model.Order;
import domain.repository.OrderRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO createOrder(CreateOrderRequest request) {
        int id = OrderMapper.generateId();
        Order order = Order.create(id, request.customerName(), request.products());
        Order saved = orderRepository.save(order);
        return OrderMapper.toDTO(saved);
    }

    @Override
    public OrderDTO getOrder(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return OrderMapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> listOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDTO)
                .toList();
    }
}
