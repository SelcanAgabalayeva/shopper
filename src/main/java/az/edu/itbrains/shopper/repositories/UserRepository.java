package az.edu.itbrains.shopper.repositories;

import az.edu.itbrains.shopper.models.Cart;
import az.edu.itbrains.shopper.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);


}
