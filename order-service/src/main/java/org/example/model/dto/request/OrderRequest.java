package org.example.model.dto.request;
import lombok.*;
import org.example.model.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Long customerId;
    private List<Long> productIds;
    private LocalDate orderDate;

    public Order toEntity(){
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setProductIds(productIds);
        order.setOrderDate(orderDate);
        return order;
    }
}
