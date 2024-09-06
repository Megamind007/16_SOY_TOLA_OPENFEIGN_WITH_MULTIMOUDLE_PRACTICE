package org.example.controller;


import org.example.dto.response.ApiResponse;
import org.example.model.dto.request.OrderRequest;
import org.example.model.dto.response.OrderResponse;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Create order successfully")
                .data(orderService.createOrder(orderRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<?> getAllOrders() {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get orders successfully")
                .data(orderService.getAllOrders())
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-order-by-id/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get order successfully")
                .data(orderService.getOrderById(id))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/update-order-by-id/{id}")
    public ResponseEntity<?> updateOrderById(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Update order successfully")
                .data(orderService.updateOrderById(id,orderRequest))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/delete-order-by-id/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Delete order successfully")
                .data(orderService.deleteOrderById(id))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
