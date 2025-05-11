package az.edu.itbrains.shopper.controllers;

import az.edu.itbrains.shopper.dtos.CartDto;
import az.edu.itbrains.shopper.dtos.CartItemDto;
import az.edu.itbrains.shopper.models.products.Product;
import az.edu.itbrains.shopper.services.CartService;
import az.edu.itbrains.shopper.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public CartController(CartService cartService, ProductService productService, ModelMapper modelMapper) {
        this.cartService = cartService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        List<CartDto> cart = (List<CartDto>) session.getAttribute("cart");
        model.addAttribute("cartItems", cart);
        return "cart"; // cart.html səhifəsinə yönləndirir
    }

    @PostMapping("/cart")
    public String addToCart(@RequestParam Long id, @RequestParam int quantity, HttpSession session) {
        Product product = productService.findById(id);

        List<CartDto> cart = (List<CartDto>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        CartDto cartDto = new CartDto(
                product.getId(),

                product.getImageUrl(),
                quantity,
                product.getPrice()
        );

        cart.add(cartDto);
        session.setAttribute("cart", cart);

        // Yeni: CartItemDto yarat və DB-yə yaz
        CartItemDto cartItemDto = new CartItemDto(

                product.getId(),
                product.getImageUrl(),
                quantity,
                product.getPrice()
        );
        cartService.addToCart(cartItemDto); // DB-ə yazılır

        return "redirect:/cart";
    }

}