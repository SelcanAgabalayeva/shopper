package az.edu.itbrains.shopper.controllers;

import az.edu.itbrains.shopper.dtos.BrandDto;
import az.edu.itbrains.shopper.dtos.CartDto;
import az.edu.itbrains.shopper.dtos.CartItemDto;
import az.edu.itbrains.shopper.dtos.SliderDto;
import az.edu.itbrains.shopper.models.Cart;
import az.edu.itbrains.shopper.models.User;
import az.edu.itbrains.shopper.models.products.Product;
import az.edu.itbrains.shopper.repositories.CartRepository;
import az.edu.itbrains.shopper.repositories.UserRepository;
import az.edu.itbrains.shopper.services.BrandService;
import az.edu.itbrains.shopper.services.CartService;
import az.edu.itbrains.shopper.services.ProductService;
import az.edu.itbrains.shopper.services.SliderService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final SliderService sliderService;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public CartController(CartService cartService, ProductService productService, SliderService sliderService, UserRepository userRepository, CartRepository cartRepository, BrandService brandService, ModelMapper modelMapper) {
        this.cartService = cartService;
        this.productService = productService;
        this.sliderService = sliderService;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<SliderDto> sliderDtoList = sliderService.getSlider();
        List<BrandDto> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        model.addAttribute("sliders", sliderDtoList);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // login olmuş istifadəçi
        User user = userRepository.findByUsername(username);

        List<Cart> cartEntities = cartRepository.findByUser(user);

        List<CartItemDto> cartItems = cartEntities.stream().map(cart1 -> {
            CartItemDto dto = new CartItemDto();
            dto.setId(cart1.getProduct().getId());
            dto.setImageUrl(cart1.getImageUrl());
            dto.setQuantity(cart1.getQuantity());
            dto.setPrice(cart1.getPrice());
          // total əlavə et
            return dto;
        }).collect(Collectors.toList());

        double subtotal = cartItems.stream().mapToDouble(CartItemDto::getTotal).sum();
        double ecoTax = 2.00;
        double vat = subtotal * 0.175;
        double total = subtotal + ecoTax + vat;

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("ecoTax", ecoTax);
        model.addAttribute("vat", vat);
        model.addAttribute("total", total);

        return "cart";
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
                product.getId(),
                product.getImageUrl(),
                quantity,
                product.getPrice()
        );
        cartService.addToCart(cartItemDto); // DB-ə yazılır

        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateOrRemoveCart(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("quantities") List<Integer> quantities,
                                     @RequestParam(value = "removeIds", required = false) List<Long> removeIds,
                                     @RequestParam("action") String action) {
        if ("remove".equals(action) && removeIds != null) {
            cartService.removeFromCart(removeIds);
        } else if ("update".equals(action)) {
            for (int i = 0; i < ids.size(); i++) {
                cartService.updateQuantity(ids.get(i), quantities.get(i));
            }
        }
        return "redirect:/cart";
    }





}