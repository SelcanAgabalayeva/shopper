package az.edu.itbrains.shopper.services;

import az.edu.itbrains.shopper.dtos.ProductDetailDto;
import az.edu.itbrains.shopper.dtos.ProductDto;
import az.edu.itbrains.shopper.models.products.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getFeaturedProducts();
    List<ProductDto> getLatestProducts();

    ProductDetailDto getProductDetail(Long id);
    List<ProductDto> getProductsByCategoryId(Long categoryId);
    List<Product> getProductsByBrandId(Long brandId);
    Product findById(Long id);

}
