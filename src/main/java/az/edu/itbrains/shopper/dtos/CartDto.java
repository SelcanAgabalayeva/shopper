package az.edu.itbrains.shopper.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;

    private String imageUrl;
    private int quantity;
    private double price;
    public double getTotal() {
        return quantity * price;
    }

}
