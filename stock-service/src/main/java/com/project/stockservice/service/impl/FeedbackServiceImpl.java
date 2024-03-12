package com.project.stockservice.service.impl;

import com.project.stockservice.entity.Order;
import com.project.stockservice.entity.OrderProduct;
import com.project.stockservice.entity.Product;
import com.project.stockservice.entity.Stock;
import com.project.stockservice.repository.OrderRepository;
import com.project.stockservice.repository.StockRepository;
import com.project.stockservice.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService
{
    private final OrderRepository orderRepository;

    private final StockRepository stockRepository;

    @Override
    public void processFeedback(String feedback)
    {
        String[] feedbackInfo = feedback.replaceAll("\"", "").split(":");

        Optional<Order> optionalOrder = orderRepository.findByOrderId(feedbackInfo[0]);

        if (optionalOrder.isPresent())
        {
            Order order = optionalOrder.get();

            if(feedbackInfo[1].equals("success"))
            {
                for (OrderProduct orderProduct : order.getOrderProducts())
                {
                    Stock stock = orderProduct.getProduct().getStock();

                    stock.setQuantity(stock.getQuantity() - orderProduct.getQuantity());

                    stockRepository.save(stock);
                }
            }

            orderRepository.delete(order);
        }
    }
}
