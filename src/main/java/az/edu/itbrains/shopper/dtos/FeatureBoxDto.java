package az.edu.itbrains.shopper.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureBoxDto {
    private Long id;
    private String firstTitle;
    private String secondTitle;
    private String imageUrl;
    private String description;
}
