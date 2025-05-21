package az.edu.itbrains.shopper.services;

import az.edu.itbrains.shopper.dtos.checkout.*;

import java.util.List;

public interface CheckoutService {
    void addCheckout(CheckoutDto checkoutDto);

    List<CheckoutDashboardDto> getCheckoutAll();

    void createCheckout(CheckoutCreateDto checkoutCreateDto);

    CheckoutGetIdDto checkoutGetIdDto(Long id);

    void updateCheckout(CheckoutUpdateDto checkoutUpdateDto, Long id);

    void deleteCheckout(Long id);

}
