package az.edu.itbrains.shopper.repositories;

import az.edu.itbrains.shopper.models.Brand;
import az.edu.itbrains.shopper.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
