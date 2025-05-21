package az.edu.itbrains.shopper.controllers.dashboard;

import az.edu.itbrains.shopper.dtos.checkout.CheckoutCreateDto;
import az.edu.itbrains.shopper.dtos.checkout.CheckoutDashboardDto;
import az.edu.itbrains.shopper.dtos.checkout.CheckoutGetIdDto;
import az.edu.itbrains.shopper.dtos.checkout.CheckoutUpdateDto;
import az.edu.itbrains.shopper.services.CheckoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CheckoutsController {
    private final CheckoutService checkoutService;
    private final ModelMapper modelMapper;
    public CheckoutsController(CheckoutService checkoutService, ModelMapper modelMapper) {
        this.checkoutService = checkoutService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/admin/checkout")
    public String getCheckoutAll(Model model){
        List<CheckoutDashboardDto> allCheckout =checkoutService.getCheckoutAll();
        model.addAttribute("checkouts", allCheckout);
        return "/dashboard/checkout/index.html";
    }

    @GetMapping("/admin/checkout/create")
    public String getCheckoutCreate(Model model){
        return "/dashboard/checkout/create.html";
    }
    @PostMapping("/admin/checkout/create")
    public String getCheckoutCreate(@ModelAttribute("contact") CheckoutCreateDto checkoutCreateDto) {
        checkoutService.createCheckout(checkoutCreateDto);
        return "redirect:/admin/checkout";

    }
    @GetMapping("/admin/checkout/update/{id}")
    public String updateCheckout(@PathVariable("id") Long id, Model model) {
        CheckoutGetIdDto checkoutGetIdDto = checkoutService.checkoutGetIdDto(id);
        model.addAttribute("checkout", checkoutGetIdDto);
        return "/dashboard/checkout/update";
    }

    @PostMapping("/admin/checkout/update/{id}")
    public String updateCheckout(@ModelAttribute CheckoutUpdateDto checkoutUpdateDto, @PathVariable("id") Long id) {
        checkoutService.updateCheckout(checkoutUpdateDto,id);
        return "redirect:/admin/checkout";

    }
    @GetMapping("/admin/checkout/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        return "/dashboard/checkout/delete";
    }
    @PostMapping("/admin/checkout/delete/{id}")
    public String removeCheckout(@PathVariable("id") Long id) {
        checkoutService.deleteCheckout(id);
        return "redirect:/admin/checkout";

    }



}
