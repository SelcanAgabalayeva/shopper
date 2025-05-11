package az.edu.itbrains.shopper.models.products;

import az.edu.itbrains.shopper.models.Brand;
import az.edu.itbrains.shopper.models.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double price;
    private String productCode;
    private String rewardPoints;
    private String availability;
    private String imageUrl;
    private boolean isFeatured;
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
}
