package application.service;

import application.dto.CreateOrderRequest;
import application.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(CreateOrderRequest request);
    OrderDTO getOrder(String id);
    List<OrderDTO> listOrders();

}
