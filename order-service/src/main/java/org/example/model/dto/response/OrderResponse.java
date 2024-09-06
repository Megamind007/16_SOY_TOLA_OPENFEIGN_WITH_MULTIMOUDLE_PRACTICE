package org.example.model.dto.response;
import lombok.*;
import org.example.dto.response.CustomerResponse;
import org.example.dto.response.ProductResponse;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderResponse {
    private Long id;
    private CustomerResponse customerResponse;
    private List<ProductResponse> productResponses;
    private LocalDate orderDate;
}
