package az.edu.itbrains.shopper.controllers;

import az.edu.itbrains.shopper.dtos.brand.BrandDto;
import az.edu.itbrains.shopper.dtos.FeatureBoxDto;
import az.edu.itbrains.shopper.dtos.product.ProductDto;
import az.edu.itbrains.shopper.dtos.SliderDto;
import az.edu.itbrains.shopper.services.BrandService;
import az.edu.itbrains.shopper.services.FeatureBoxService;
import az.edu.itbrains.shopper.services.ProductService;
import az.edu.itbrains.shopper.services.SliderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final SliderService sliderService;
    private final ProductService productService;
    private final FeatureBoxService featureBoxService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public HomeController(SliderService sliderService, ProductService productService, FeatureBoxService featureBoxService, BrandService brandService, ModelMapper modelMapper) {
        this.sliderService = sliderService;
        this.productService = productService;
        this.featureBoxService = featureBoxService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<SliderDto> sliderDtoList=sliderService.getSlider();
        List<ProductDto> featuredProducts = productService.getFeaturedProducts();
        List<ProductDto> latestProducts =productService.getLatestProducts();
        List<FeatureBoxDto> featureBoxDtoList=featureBoxService.getAllFeatureBox();
        List<BrandDto> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);

        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("latestProducts", latestProducts);

        model.addAttribute("sliders",sliderDtoList);
        model.addAttribute("boxs",featureBoxDtoList);
        return "index.html";

    }
    @GetMapping("/contact")
    public String contact() {
        return "contact.html";

    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        List<SliderDto> sliderDtoList=sliderService.getSlider();
        model.addAttribute("sliders",sliderDtoList);
        return "checkout.html";

    }

    @GetMapping("/products")
    public String products() {
        return "products.html";

    }
    @GetMapping("/product_detail")
    public String product_detail(Model model) {
        List<ProductDto> featuredProducts = productService.getFeaturedProducts();

        model.addAttribute("featuredProducts", featuredProducts);
        return "product_detail.html";

    }

}
