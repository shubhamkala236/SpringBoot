package org.shub.springecom.service;

import org.aspectj.weaver.ast.Or;
import org.shub.springecom.model.Order;
import org.shub.springecom.model.OrderItem;
import org.shub.springecom.model.Product;
import org.shub.springecom.model.dto.OrderItemResponse;
import org.shub.springecom.model.dto.OrderRequest;
import org.shub.springecom.model.dto.OrderResponse;
import org.shub.springecom.repo.OrderRepo;
import org.shub.springecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;

    public OrderResponse placeOrder(OrderRequest req) {
        Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(req.customerName());
        order.setEmail(req.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();

        for(var reqItem : req.items()) {
            Product product = productRepo.findById(reqItem.productId())
                    .orElseThrow(() -> new RuntimeException("Product nott found"));

            product.setStockQuantity(product.getStockQuantity() - reqItem.quantity());
            productRepo.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(reqItem.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(reqItem.quantity())))
                    .order(order)
                    .build();

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        var savedOrder = orderRepo.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();

        for(OrderItem item : order.getItems())
        {
            OrderItemResponse orderItemResponse = new OrderItemResponse(item.getProduct().getName(), item.getQuantity(), item.getTotalPrice());
        }

        OrderResponse orderResponse = new OrderResponse(savedOrder.getOrderId(), savedOrder.getCustomerName(), savedOrder.getEmail(), savedOrder.getStatus(), savedOrder.getOrderDate(), itemResponses);

        return orderResponse;
    }

    public List<OrderResponse> getAllOrderResponses() {
        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for(Order order : orders)
        {
            List<OrderItemResponse> itemResponses = new ArrayList<>();

            for(OrderItem item : order.getItems())
            {
                OrderItemResponse orderItemResponse = new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                );
                itemResponses.add(orderItemResponse);
            }

            OrderResponse orderResponse = new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    itemResponses
            );
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }
}
