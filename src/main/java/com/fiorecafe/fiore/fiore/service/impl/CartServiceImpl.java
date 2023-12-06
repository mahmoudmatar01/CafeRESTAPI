package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.dto.request.AddToCartRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.CartItemResponseDto;
import com.fiorecafe.fiore.fiore.dto.response.CartResponseDto;
import com.fiorecafe.fiore.fiore.entity.cart.Cart;
import com.fiorecafe.fiore.fiore.entity.cart.CartItem;
import com.fiorecafe.fiore.fiore.entity.item.Item;
import com.fiorecafe.fiore.fiore.entity.user.User;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.mapper.CartItemToCartItemResponseDto;
import com.fiorecafe.fiore.fiore.mapper.CartToCartResponseDto;
import com.fiorecafe.fiore.fiore.repository.cart.CartItemRepository;
import com.fiorecafe.fiore.fiore.repository.cart.CartRepository;
import com.fiorecafe.fiore.fiore.repository.item.ItemRepository;
import com.fiorecafe.fiore.fiore.repository.user.UserRepository;
import com.fiorecafe.fiore.fiore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartItemToCartItemResponseDto toCartItemResponseDto;
    private final CartToCartResponseDto cartToCartResponseDto;
    @Override
    public CartResponseDto createCart(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundResourceException("there is no user with this id")
        );
        Cart cart = Cart.builder().user(user).build();
        cartRepository.save(cart);
        return cartToCartResponseDto.apply(cart);
    }

    @Override
    public CartResponseDto getCartByUser(Long userId) {
        Cart cart= cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundResourceException("Cart not found for user"));
        return cartToCartResponseDto.apply(cart);
    }

    @Override
    public CartItemResponseDto addToCart(AddToCartRequestDto dto) {
        Cart cart= cartRepository.findByUserId(dto.userId())
                .orElseThrow(() -> new NotFoundResourceException("Cart not found for user"));
        Item item=itemRepository.findById(dto.itemId()).orElseThrow(
                ()->new NotFoundResourceException("there is no item with this name")
        );

        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndItem(cart, item);

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + dto.quantity());
            cartItemRepository.save(cartItem);
            return  toCartItemResponseDto.apply(cartItem);
        } else {
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .item(item)
                    .quantity(dto.quantity())
                    .build();
            cartItemRepository.save(cartItem);
            return  toCartItemResponseDto.apply(cartItem);
        }
    }

    @Override
    public CartItemResponseDto removeFromCart(Long userId, Long itemId) {
        Cart cart= cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundResourceException("Cart not found for user"));
        Item item=itemRepository.findById(itemId).orElseThrow(
                ()->new NotFoundResourceException("there is no item with this name")
        );
        Optional<CartItem> optionalCartItem = cartItemRepository.findByCartAndItem(cart, item);

        if (optionalCartItem.isPresent()) {
            CartItem removedCartItem = optionalCartItem.get();
            cartItemRepository.delete(removedCartItem);
            return  toCartItemResponseDto.apply(removedCartItem);
        } else {
            throw new BadRequestException("something went wrong");
        }
    }
}
