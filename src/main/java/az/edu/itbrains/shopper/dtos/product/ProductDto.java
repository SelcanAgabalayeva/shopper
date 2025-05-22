package az.edu.itbrains.shopper.dtos.product;

import az.edu.itbrains.shopper.dtos.brand.BrandDto;
import az.edu.itbrains.shopper.dtos.category.CategoryDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String imageUrl;
    private boolean isFeatured;
    private Date createdDate;
    private String productCode;
    private String rewardPoints;
    private String availability;
    private CategoryDto category;
    private BrandDto brand;

}
