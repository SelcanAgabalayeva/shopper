package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.CartDto;
import az.edu.itbrains.shopper.dtos.CartItemDto;
import az.edu.itbrains.shopper.models.Cart;
import az.edu.itbrains.shopper.models.User;
import az.edu.itbrains.shopper.models.products.Product;
import az.edu.itbrains.shopper.repositories.CartRepository;
import az.edu.itbrains.shopper.repositories.ProductRepository;
import az.edu.itbrains.shopper.repositories.UserRepository;
import az.edu.itbrains.shopper.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class CartServiceImpl implements CartService {
    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  CartRepository cartRepository;
    @Autowired
    private  UserRepository userRepository;


    public CartServiceImpl(ProductRepository productRepository, CartRepository cartRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addToCart(CartItemDto itemDto) {
        try {
            Cart cart = new Cart();

            cart.setImageUrl(itemDto.getImageUrl());
            cart.setQuantity(itemDto.getQuantity());
            cart.setPrice(itemDto.getPrice());

            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + itemDto.getProductId()));
            cart.setProduct(product);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName(); // DƏYİŞDİ
            User user = userRepository.findByEmail(email); // DƏYİŞDİ

            if (user != null) {
                cart.setUser(user);
            } else {
                throw new RuntimeException("User not found.");
            }

            cartRepository.save(cart);
            log.info("Cart item saved successfully: {}", cart);
        } catch (Exception e) {
            log.error("Error saving cart item: ", e);
        }
    }

    @Override
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    @Override
    public List<CartItemDto> convertCartToView(List<CartDto> cartItems) {
        return cartItems.stream().map(item -> new CartItemDto(

                item.getId(),
                item.getImageUrl(),
                item.getQuantity(),
                item.getPrice()
        )).collect(Collectors.toList());
    }

}
