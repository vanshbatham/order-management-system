package com.oms.service;

import com.oms.dto.request.CreateOrderRequest;
import com.oms.dto.request.CustomerRequest;
import com.oms.dto.request.OrderItemRequest;
import com.oms.dto.response.CustomerResponse;
import com.oms.dto.response.OrderItemResponse;
import com.oms.dto.response.OrderResponse;
import com.oms.model.*;
import com.oms.repository.CustomerRepository;
import com.oms.repository.ProductRepository;
import com.oms.repository.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final SalesOrderRepository salesOrderRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    // customer operations
    public CustomerResponse createCustomer(CustomerRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Customer with this email already exists");
        }

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        return mapToCustomerResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToCustomerResponse)
                .collect(Collectors.toList());
    }

    // order operations
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {

        // validate customer exists
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));

        // build order
        SalesOrder order = new SalesOrder();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        // build order items + calculate total
        double total = 0.0;

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + itemRequest.getProductId()));

            // check stock availability
            if (product.getQuantityInStock() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getProductName()
                        + ". Available: " + product.getQuantityInStock());
            }

            // deduct stock
            product.setQuantityInStock(product.getQuantityInStock() - itemRequest.getQuantity());
            productRepository.save(product);

            // build item
            SalesOrderItem item = new SalesOrderItem();
            item.setSalesOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setUnitPrice(product.getUnitPrice()); // snapshot price
            order.getItems().add(item);

            total += product.getUnitPrice() * itemRequest.getQuantity();
        }

        order.setTotalAmount(total);

        return mapToOrderResponse(salesOrderRepository.save(order));
    }

    public List<OrderResponse> getAllOrders() {
        return salesOrderRepository.findAll()
                .stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Long id) {
        SalesOrder order = salesOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return mapToOrderResponse(order);
    }

    public OrderResponse updateOrderStatus(Long id, String status) {
        SalesOrder order = salesOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        return mapToOrderResponse(salesOrderRepository.save(order));
    }

    @Transactional
    public void cancelOrder(Long id) {
        SalesOrder order = salesOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Cannot cancel a delivered order");
        }

        // restore stock for each item
        for (SalesOrderItem item : order.getItems()) {
            Product product = item.getProduct();
            product.setQuantityInStock(product.getQuantityInStock() + item.getQuantity());
            productRepository.save(product);
        }

        order.setStatus(OrderStatus.CANCELLED);
        salesOrderRepository.save(order);
    }

    // mapping methods
    private OrderResponse mapToOrderResponse(SalesOrder order) {
        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(item -> new OrderItemResponse(
                        item.getSalesOrderItemId(),
                        item.getProduct().getProductId(),
                        item.getProduct().getProductName(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getUnitPrice() * item.getQuantity()
                ))
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getSalesOrderId(),
                order.getCustomer().getCustomerId(),
                order.getCustomer().getName(),
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus().name(),
                items
        );
    }

    private CustomerResponse mapToCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }
}