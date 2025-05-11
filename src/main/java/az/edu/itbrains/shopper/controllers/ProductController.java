package az.edu.itbrains.shopper.controllers;

import az.edu.itbrains.shopper.dtos.BrandDto;
import az.edu.itbrains.shopper.dtos.ProductDetailDto;
import az.edu.itbrains.shopper.dtos.ProductDto;
import az.edu.itbrains.shopper.models.products.Product;
import az.edu.itbrains.shopper.services.BrandService;
import az.edu.itbrains.shopper.services.ProductService;
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

    public ProductController(ProductService productService, BrandService brandService) {
        this.productService = productService;
        this.brandService = brandService;
    }

    @GetMapping("/products/detail/{id}")
    public String products(@PathVariable Long id, Model model) {
        ProductDetailDto productDetailDto=productService.getProductDetail(id);
        List<ProductDto> featuredProducts = productService.getFeaturedProducts();
        List<BrandDto> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        model.addAttribute("product",productDetailDto);
        model.addAttribute("featuredProducts", featuredProducts);
        return "product_detail.html";
    }
    @GetMapping("/products/category/{id}")
    public String getProductsByCategory(
            @PathVariable("id") Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        List<BrandDto> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        List<ProductDto> allProducts = productService.getProductsByCategoryId(categoryId);
        int pageSize = 3;
        int totalProducts = allProducts.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalProducts);

        List<ProductDto> paginatedProducts = allProducts.subList(start, end);

        model.addAttribute("products", paginatedProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("categoryId", categoryId);

        return "products.html";
    }
    @GetMapping("/products/brand/{id}")
    public String getProductsByBrand(
            @PathVariable("id") Long brandId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        List<BrandDto> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);

        List<Product> allProducts = productService.getProductsByBrandId(brandId);
        int pageSize = 3;
        int totalProducts = allProducts.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalProducts);
        List<Product> paginatedProducts = allProducts.subList(start, end);

        model.addAttribute("products", paginatedProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("brandId", brandId);

        return "products_brand.html";
    }




}
