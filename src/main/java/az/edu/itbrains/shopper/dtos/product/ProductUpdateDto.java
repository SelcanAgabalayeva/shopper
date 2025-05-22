package az.edu.itbrains.shopper.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private Long id;
    private String title;
    private double price;
    private String productCode;
    private String rewardPoints;
    private String availability;
    private String imageUrl;
    private String description;
    private String size;
    private String color;
    private List<String> sizes;
    private List<String> colours;
    private boolean isFeatured;
    private Date createdDate;
    private Long brandId;
    private Long categoryId;
}
