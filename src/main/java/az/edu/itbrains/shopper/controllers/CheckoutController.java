package az.edu.itbrains.shopper.controllers;

import az.edu.itbrains.shopper.dtos.checkout.CheckoutDto;
import az.edu.itbrains.shopper.services.CheckoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {
    private final CheckoutService checkoutService;
    private final ModelMapper modelMapper;

    public CheckoutController(CheckoutService checkoutService, ModelMapper modelMapper) {
        this.checkoutService = checkoutService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/checkout")
    public String addCheckout(@ModelAttribute("checkout")CheckoutDto checkoutDto){
        checkoutService.addCheckout(checkoutDto);
        return "redirect:/checkout";
    }
}
