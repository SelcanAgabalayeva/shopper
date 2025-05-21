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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);

        Product product = productRepository.findById(itemDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart existing = cartRepository.findByUserAndProduct(user, product);
        if (existing != null) {
            // Eyni məhsul varsa - quantity artır
            int newQuantity = existing.getQuantity() + itemDto.getQuantity();
            existing.setQuantity(newQuantity);

            cartRepository.save(existing);
        } else {
            // Yeni cart item
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setImageUrl(itemDto.getImageUrl());
            cart.setQuantity(itemDto.getQuantity());
            cart.setPrice(itemDto.getPrice());

            cartRepository.save(cart);
        }
    }

    @Override
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    @Override
    public void updateQuantity(Long productId, int quantity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);

        List<Cart> cartItems = cartRepository.findAllByUserAndProductId(user, productId);

        if (!cartItems.isEmpty()) {
            Cart mainCart = cartItems.get(0);
            mainCart.setQuantity(quantity);

            cartRepository.save(mainCart);

            // Əgər eyni məhsuldan birdən çox varsa, artıq olanları sil
            for (int i = 1; i < cartItems.size(); i++) {
                cartRepository.delete(cartItems.get(i));
            }
        }
    }

    @Override
    public void removeFromCart(List<Long> productIds) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new RuntimeException("User is not authenticated.");
        }

        String username = auth.getName();
        User user = userRepository.findByUsername(username);

        for (Long productId : productIds) {
            List<Cart> items = cartRepository.findAllByUserAndProductId(user, productId);
            if (!items.isEmpty()) {
                cartRepository.deleteAll(items);
            }
        }
    }


    @Override
    public List<CartItemDto> convertCartToView(List<CartDto> cartItems) {
        return cartItems.stream().map(item -> new CartItemDto(
                item.getId(),
                item.getId(),
                item.getImageUrl(),
                item.getQuantity(),
                item.getPrice()
        )).collect(Collectors.toList());
    }


}
