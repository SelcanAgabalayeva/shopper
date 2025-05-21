package az.edu.itbrains.shopper.dtos;

import az.edu.itbrains.shopper.dtos.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRelatedDto {
    private Long id;
    private String title;
    private double price;
    private String imageUrl;
    private CategoryDto category;
}
