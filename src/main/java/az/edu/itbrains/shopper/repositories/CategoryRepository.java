package az.edu.itbrains.shopper.repositories;

import az.edu.itbrains.shopper.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByIsNavbarTrue();

}
