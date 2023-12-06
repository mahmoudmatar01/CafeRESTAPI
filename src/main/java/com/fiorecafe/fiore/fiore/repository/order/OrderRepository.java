package com.fiorecafe.fiore.fiore.repository.order;

import com.fiorecafe.fiore.fiore.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}