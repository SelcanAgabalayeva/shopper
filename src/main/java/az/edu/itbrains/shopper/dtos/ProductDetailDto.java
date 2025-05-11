package az.edu.itbrains.shopper.dtos;

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
    private CategoryDto category;
    private BrandDto brand;

}
