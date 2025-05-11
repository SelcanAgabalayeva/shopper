package az.edu.itbrains.shopper.repositories;

import az.edu.itbrains.shopper.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByIsFeaturedTrue();
    List<Product> findTop4ByOrderByCreatedDateDesc();
    List<Product> findByIsFeaturedFalseOrderByCreatedDateDesc();
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByBrandId(Long brandId);

}
