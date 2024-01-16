package com.fnnek.bigcompany.service;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.fnnek.bigcompany.mapper.OrderMapper;
import com.fnnek.bigcompany.model.Order;

@Service
public class NewOrderService {
    
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public NewOrderService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void printAllOrders() {
        var sql = "SELECT * FROM ORDERS";

        jdbcTemplate.getJdbcTemplate().setFetchSize(1000);
        //dbcTemplate.getJdbcTemplate().setLazyInit(true);
        AtomicInteger count = new AtomicInteger();
        try (Stream<Order> orders = jdbcTemplate.queryForStream(sql, new HashMap<>(), new OrderMapper())) {
            orders.forEach(order -> {
                System.out.println(count.getAndIncrement() + ": " +order.name());
            });
        }
    }

    public void printAllOrders2() {
        var sql = "SELECT * FROM ORDERS";

        //jdbcTemplate.getJdbcTemplate().setFetchSize(10000);
        //dbcTemplate.getJdbcTemplate().setLazyInit(true);
        AtomicInteger count = new AtomicInteger();
        jdbcTemplate.query(sql, (rs) -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("2nd: " + count.getAndIncrement() + ": " + rs.getString(2));
        });
    }
}
