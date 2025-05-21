package az.edu.itbrains.shopper.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "checkout")
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String fax;
    private String company;
    private String companyId;
    private String address1;
    private String address2;
    private String city;
    private String postCode;
    private String country;
    private String region;
    private String comment;
}
