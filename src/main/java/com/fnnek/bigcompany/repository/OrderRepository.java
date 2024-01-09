package com.fnnek.bigcompany.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import com.fnnek.bigcompany.entity.OrderEntity;
import jakarta.persistence.QueryHint;

public interface OrderRepository extends CrudRepository<OrderEntity, Long>{
    
    @Query("SELECT o from OrderEntity o")
    List<OrderEntity> getOrders();

    @Query("SELECT o from OrderEntity o")
    @QueryHints(value = {
        @QueryHint(name = "org.hibernate.fetchSize", value = "100000"),
        @QueryHint(name = "org.hibernate.cacheable", value = "false"),
        @QueryHint(name = "org.hibernate.readOnly", value = "true")
        })
    Stream<OrderEntity> getOrdersStream();
}
