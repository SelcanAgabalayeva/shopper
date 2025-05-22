package az.edu.itbrains.shopper.dtos.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandCreateDto {

    private String name;
    private String imageUrl;
}
