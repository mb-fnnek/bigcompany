package com.fnnek.bigcompany.service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fnnek.bigcompany.entity.OrderEntity;
import com.fnnek.bigcompany.mapper.Mapper;
import com.fnnek.bigcompany.model.Order;
import com.fnnek.bigcompany.repository.OrderRepository;

import jakarta.persistence.EntityManager;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;

    public OrderService(OrderRepository orderRepository, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
    }

    public void saveOrder(Order order) {
        orderRepository.save(Mapper.mapOrderEntity(order));
    }

    @Transactional(readOnly = true)
    public void printAllOrdersStream() {
        try (Stream<OrderEntity> companies = orderRepository.getOrdersStream()) {
            AtomicInteger count = new AtomicInteger(1);
            companies.forEach(e -> {
                System.out.println(count.incrementAndGet());
                entityManager.detach(e);
            });
        }
    }

    @Transactional(readOnly = true)
    public void printAllOrders() {
        AtomicInteger count = new AtomicInteger(1);
        //orderRepository.getOrdersStream().forEach();
    }

    private void printOrderName(OrderEntity orderEntity) {
        System.out.println(orderEntity.getName());
    }

    private void printAndDetach(OrderEntity orderEntity) {
        printOrderName(orderEntity);
        entityManager.detach(orderEntity);
    }
}
