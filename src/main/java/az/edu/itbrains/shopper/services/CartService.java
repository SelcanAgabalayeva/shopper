package az.edu.itbrains.shopper.services;

import az.edu.itbrains.shopper.dtos.CartDto;
import az.edu.itbrains.shopper.dtos.CartItemDto;
import az.edu.itbrains.shopper.models.Cart;

import java.util.List;

public interface CartService {
    List<CartItemDto> convertCartToView(List<CartDto> cartItems);
    void addToCart(CartItemDto itemDto);
    List<Cart> getAllCartItems();

}
