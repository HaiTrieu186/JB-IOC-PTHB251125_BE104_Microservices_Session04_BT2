package re.edu.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.orderservice.dto.request.OrderRequestDTO;
import re.edu.orderservice.dto.response.OrderResponseDTO;
import re.edu.orderservice.entity.Order;
import re.edu.orderservice.exception.ResourceNotFoundException;
import re.edu.orderservice.repository.OrderRepository;
import re.edu.orderservice.service.IOrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        // Giả lập giá sản phẩm lấy từ Product Service (Ví dụ: 50,000 VNĐ)
        BigDecimal mockPrice = new BigDecimal("50000");

        // Tính tổng tiền = giá * số lượng
        BigDecimal total = mockPrice.multiply(new BigDecimal(dto.getQuantity()));

        Order order = new Order();
        order.setCustomerId(dto.getCustomerId());
        order.setProductId(dto.getProductId());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(total);

        Order savedOrder;
        try {
            // Cố gắng lưu vào PostgreSQL
            savedOrder = orderRepository.save(order);
        } catch (Exception e) {
            // Ném lỗi ra để GlobalExceptionHandler tóm lại và trả về 500
            throw new RuntimeException("Không thể lưu đơn hàng vào Database!");
        }

        return mapToResponse(savedOrder);
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn hàng với id: " + id));
        return mapToResponse(order);
    }

    private OrderResponseDTO mapToResponse(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .build();
    }
}
