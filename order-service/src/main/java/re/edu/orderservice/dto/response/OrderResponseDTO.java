package re.edu.orderservice.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDTO {
    private Long id;
    private Long customerId;
    private Long productId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
}