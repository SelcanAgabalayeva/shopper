package az.edu.itbrains.shopper.controllers;

import az.edu.itbrains.shopper.dtos.*;
import az.edu.itbrains.shopper.models.products.Product;
import az.edu.itbrains.shopper.services.BrandService;
import az.edu.itbrains.shopper.services.ProductService;
import az.edu.itbrains.shopper.services.SliderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final BrandService brandService;
    private final SliderService sliderService;

    public ProductController(ProductService productService, BrandService brandService, SliderService sliderService) {
        this.productService = productService;
        this.brandService = brandService;
        this.sliderService = sliderService;
    }

    @GetMapping("/products/detail/{id}")
    public String products(@PathVariable Long id, Model model) {
        ProductDetailDto productDetailDto=productService.getProductDetail(id);
        List<ProductDto> featuredProducts = productService.getFeaturedProducts();
        List<BrandDto> brands = brandService.getAllBrands();
        List<SliderDto> sliderDtoList=sliderService.getSlider();
        List<ProductRelatedDto> productRelatedDtoList=productService.getProductRelated();
        model.addAttribute("brands", brands);
        model.addAttribute("product",productDetailDto);
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("sliders",sliderDtoList);
        model.addAttribute("related",productRelatedDtoList);
        return "product_detail.html";
    }
    @GetMapping("/products/category/{id}")
    public String getProductsByCategory(
            @PathVariable("id") Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        List<SliderDto> sliderDtoList=sliderService.getSlider();
        List<BrandDto> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        List<ProductDto> allProducts = productService.getProductsByCategoryId(categoryId);
        List<ProductDto> featuredProducts = productService.getFeaturedProducts();
        int pageSize = 3;
        int totalProducts = allProducts.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalProducts);

        List<ProductDto> paginatedProducts = allProducts.subList(start, end);
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("products", paginatedProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("sliders",sliderDtoList);
        model.addAttribute("categoryId", categoryId);

        return "products.html";
    }
    @GetMapping("/products/brand/{id}")
    public String getProductsByBrand(
            @PathVariable("id") Long brandId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        List<SliderDto> sliderDtoList=sliderService.getSlider();
        List<BrandDto> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        List<ProductDto> featuredProducts = productService.getFeaturedProducts();
        List<Product> allProducts = productService.getProductsByBrandId(brandId);
        int pageSize = 3;
        int totalProducts = allProducts.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalProducts);
        List<Product> paginatedProducts = allProducts.subList(start, end);
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("products", paginatedProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sliders",sliderDtoList);
        model.addAttribute("brandId", brandId);

        return "products_brand.html";
    }




}
