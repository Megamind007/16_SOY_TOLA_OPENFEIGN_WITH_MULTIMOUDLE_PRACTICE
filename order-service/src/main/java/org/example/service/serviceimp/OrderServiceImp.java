package org.example.service.serviceimp;

import org.example.client.CustomerClient;
import org.example.client.ProductClient;
import org.example.dto.response.ApiResponse;
import org.example.model.Order;
import org.example.model.dto.request.OrderRequest;
import org.example.dto.response.CustomerResponse;
import org.example.model.dto.response.OrderResponse;
import org.example.dto.response.ProductResponse;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public OrderServiceImp(OrderRepository orderRepository, CustomerClient customerClient, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        ApiResponse<CustomerResponse> customerResponse = customerClient.getCustomerById(orderRequest.getCustomerId());
        CustomerResponse customer = customerResponse.getData();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Long productId : orderRequest.getProductIds()) {
            ApiResponse<ProductResponse> product = productClient.getProductById(productId);
            if (product.getData() != null) {
                productResponses.add(product.getData());
            }
        }
        Order order = orderRepository.save(orderRequest.toEntity());
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setCustomerResponse(customer);
        orderResponse.setProductResponses(productResponses);
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> listOrderResponse = new ArrayList<>();
        for (Order order : orders) {
            OrderResponse orderResponse = new OrderResponse();
            ApiResponse<CustomerResponse> customerResponse = customerClient.getCustomerById(order.getCustomerId());
            CustomerResponse customer = customerResponse.getData();
            List<ProductResponse> productResponses = new ArrayList<>();
            for (Long productId : order.getProductIds()) {
                ApiResponse<ProductResponse> product = productClient.getProductById(productId);
                if (product.getData() != null) {
                    productResponses.add(product.getData());
                }
            }
            orderResponse.setId(order.getId());
            orderResponse.setCustomerResponse(customer);
            orderResponse.setProductResponses(productResponses);
            orderResponse.setOrderDate(order.getOrderDate());
            listOrderResponse.add(orderResponse);
        }
        return listOrderResponse;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        assert order != null;
        ApiResponse<CustomerResponse> customerResponse = customerClient.getCustomerById(order.getCustomerId());
        CustomerResponse customer = customerResponse.getData();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Long productId : order.getProductIds()) {
            ApiResponse<ProductResponse> productResponse = productClient.getProductById(productId);
            if (productResponse != null && productResponse.getData() != null) {
                productResponses.add(productResponse.getData());
            }
        }
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomerResponse(customer);
        orderResponse.setProductResponses(productResponses);
        orderResponse.setOrderDate(order.getOrderDate());
        return orderResponse;
    }

    @Override
    public OrderResponse updateOrderById(Long orderId, OrderRequest updatedOrderRequest) {
        Order existingOrder = orderRepository.findById(orderId).orElse(null);
        assert existingOrder != null;
        existingOrder.setCustomerId(updatedOrderRequest.getCustomerId());
        existingOrder.setProductIds(updatedOrderRequest.getProductIds());
        existingOrder.setOrderDate(updatedOrderRequest.getOrderDate());
        orderRepository.save(existingOrder);
        ApiResponse<CustomerResponse> customerResponse = customerClient.getCustomerById(existingOrder.getCustomerId());
        CustomerResponse customer = customerResponse.getData();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Long productId : existingOrder.getProductIds()) {
            ApiResponse<ProductResponse> productResponse = productClient.getProductById(productId);
            if (productResponse != null && productResponse.getData() != null) {
                productResponses.add(productResponse.getData());
            }
        }
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(existingOrder.getId());
        orderResponse.setCustomerResponse(customer);
        orderResponse.setProductResponses(productResponses);
        orderResponse.setOrderDate(existingOrder.getOrderDate());
        return orderResponse;
    }

    @Override
    public OrderResponse deleteOrderById(Long id) {
        orderRepository.deleteById(id);
        return null;
    }
}
