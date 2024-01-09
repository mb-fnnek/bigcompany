package com.fnnek.bigcompany.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fnnek.bigcompany.service.CompanyService;
import com.fnnek.bigcompany.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/list")
    public ResponseEntity populateCompaniesAndClients() {
        orderService.printAllOrders();
        
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stream")
    public ResponseEntity populateOrders() {
        orderService.printAllOrdersStream();
        
        return ResponseEntity.ok().build();
    }
}
