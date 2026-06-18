package re.edu.orderservice.service;

import re.edu.orderservice.dto.request.OrderRequestDTO;
import re.edu.orderservice.dto.response.OrderResponseDTO;

public interface IOrderService {
    OrderResponseDTO createOrder(OrderRequestDTO requestDTO);
    OrderResponseDTO getOrderById(Long id);
}
