package org.example.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_tb")
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    @ElementCollection
    @CollectionTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"))
    private List<Long> productIds;
    private LocalDate orderDate;
}
