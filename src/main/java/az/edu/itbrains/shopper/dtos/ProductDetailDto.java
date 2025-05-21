package az.edu.itbrains.shopper.dtos;

import az.edu.itbrains.shopper.dtos.category.CategoryDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {
    private Long id;
    private String title;
    private double price;
    private String imageUrl;
    private boolean isFeatured;
    private Date createdDate;
    private String productCode;
    private String rewardPoints;
    private String availability;
    private String description;
    private String size;
    private String color;
    private List<String> sizes;
    private List<String> colours;
    private CategoryDto category;
    private BrandDto brand;

}
