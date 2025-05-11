package az.edu.itbrains.shopper.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SliderDto {
    private Long id;
    private String imageUrl;
    private String title;
    private String subtitle;
    private String description;
}
