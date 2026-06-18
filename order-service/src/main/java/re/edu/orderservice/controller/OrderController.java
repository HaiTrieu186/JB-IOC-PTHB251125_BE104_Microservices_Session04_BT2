package re.edu.orderservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.orderservice.dto.request.OrderRequestDTO;
import re.edu.orderservice.dto.response.ApiResponse;
import re.edu.orderservice.dto.response.OrderResponseDTO;
import re.edu.orderservice.service.IOrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDTO>> createOrder(@Valid @RequestBody OrderRequestDTO requestDTO) {
        OrderResponseDTO data = orderService.createOrder(requestDTO);
        ApiResponse<OrderResponseDTO> response = ApiResponse.success("Tạo đơn hàng thành công", data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOrderById(@PathVariable Long id) {
        OrderResponseDTO data = orderService.getOrderById(id);
        ApiResponse<OrderResponseDTO> response = ApiResponse.success("Lấy thông tin đơn hàng thành công", data);
        return ResponseEntity.ok(response);
    }
}