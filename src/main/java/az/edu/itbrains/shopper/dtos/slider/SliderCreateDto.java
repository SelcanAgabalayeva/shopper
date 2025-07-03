package az.edu.itbrains.shopper.dtos.slider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SliderCreateDto {
    private String imageUrl;
    private String title;
    private String subtitle;
    private String description;
}
