package az.edu.itbrains.shopper.dtos.checkout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDashboardDto {
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
