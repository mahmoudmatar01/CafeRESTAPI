package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.entity.cart.CartItem;
import com.fiorecafe.fiore.fiore.entity.order.Order;
import com.fiorecafe.fiore.fiore.entity.order.OrderItem;
import com.fiorecafe.fiore.fiore.entity.user.User;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.repository.cart.CartItemRepository;
import com.fiorecafe.fiore.fiore.repository.order.OrderRepository;
import com.fiorecafe.fiore.fiore.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public Order createOrder(Long userId, List<CartItem> cartItems) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundResourceException("there is no user with this id")
        );
        Order order = Order.builder()
                            .user(user)
                            .orderItems(convertCartItemsToOrderItems(cartItems, user))
                            .build();
        return orderRepository.save(order);
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private List<OrderItem> convertCartItemsToOrderItems(List<CartItem> cartItems, User user) {
        return cartItems.stream()
                .map(cartItem -> OrderItem.builder()
                        .order(null)
                        .item(cartItem.getItem())
                        .quantity(cartItem.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
    public boolean confirmOrder(Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(
                    ()-> new NotFoundResourceException("there is no user with this id")
            );
            List<CartItem> cartItems = cartItemRepository.findByCartUser(user.getCart().getCartId());
            if (!cartItems.isEmpty()) {
                Order order = createOrder(user.getUserId(), cartItems);
                return order != null;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
