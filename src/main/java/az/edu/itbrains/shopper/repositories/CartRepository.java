package az.edu.itbrains.shopper.repositories;

import az.edu.itbrains.shopper.dtos.CartItemDto;
import az.edu.itbrains.shopper.models.Cart;
import az.edu.itbrains.shopper.models.User;
import az.edu.itbrains.shopper.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByUser(User user);
    Cart findByUserAndProduct(User user, Product product);
    List<Cart> findByUserId(Long userId);


    Cart findByUserAndProductId(User user, Long productId);


    List<Cart> findAllByUserAndProductId(User user, Long productId);

}
