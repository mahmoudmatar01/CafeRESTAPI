package com.fiorecafe.fiore.fiore.controller;

import com.fiorecafe.fiore.fiore.entity.order.Order;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory200;
import com.fiorecafe.fiore.fiore.service.AuthService;
import com.fiorecafe.fiore.fiore.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/order")
@RequiredArgsConstructor
public class OrderController {
    private final ResponseFactory200 successFactory;
    private final OrderServiceImpl orderService;

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmOrder(@RequestParam Long userId) {
        Object response=new String("Failed to confirm order.");
        if (orderService.confirmOrder(userId)) {
             response="Order confirmed successfully.";
            return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(response));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(successFactory.createResponse(response));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (!orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(orders));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
