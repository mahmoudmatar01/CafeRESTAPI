package com.fiorecafe.fiore.fiore.repository.cart;

import com.fiorecafe.fiore.fiore.entity.cart.Cart;
import com.fiorecafe.fiore.fiore.entity.cart.CartItem;
import com.fiorecafe.fiore.fiore.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndItem(Cart cart, Item item);
    @Query(value = "SELECT * FROM cart_items c WHERE c.cart_id = :cartId", nativeQuery = true)
    List<CartItem> findByCartUser(@Param("cartId") Long cartId);
}
