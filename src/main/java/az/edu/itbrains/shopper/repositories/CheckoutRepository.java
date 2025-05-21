package az.edu.itbrains.shopper.repositories;

import az.edu.itbrains.shopper.models.Category;
import az.edu.itbrains.shopper.models.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout,Long> {
}
