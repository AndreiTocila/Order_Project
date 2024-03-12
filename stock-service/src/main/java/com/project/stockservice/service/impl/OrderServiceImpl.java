package com.project.stockservice.service.impl;

import com.project.order.dto.OrderKafkaDTO;
import com.project.stockservice.dto.OrderDTO;
import com.project.stockservice.entity.Order;
import com.project.stockservice.entity.OrderProduct;
import com.project.stockservice.entity.Product;
import com.project.stockservice.repository.OrderRepository;
import com.project.stockservice.repository.ProductRepository;
import com.project.stockservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService
{
    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Override
    public void saveTempOrder(OrderKafkaDTO orderKafkaDTO)
    {
        Order order = Order.builder()
                .orderId(orderKafkaDTO.getId())
                .build();

        List<Product> products = productRepository.findAllById(orderKafkaDTO.getProductIds());
        List<Integer> quantities = orderKafkaDTO.getProductQuantities();

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (int i = 0; i < products.size(); ++i)
        {
            OrderProduct orderProduct = OrderProduct.builder()
                    .product(products.get(i))
                    .order(order)
                    .quantity(quantities.get(i))
                    .build();
            orderProducts.add(orderProduct);
        }
        order.setOrderProducts(orderProducts);

        orderRepository.save(order);
    }
}
