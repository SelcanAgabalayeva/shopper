package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.BrandDto;
import az.edu.itbrains.shopper.dtos.CategoryDto;
import az.edu.itbrains.shopper.dtos.ProductDetailDto;
import az.edu.itbrains.shopper.dtos.ProductDto;
import az.edu.itbrains.shopper.models.products.Product;
import az.edu.itbrains.shopper.repositories.ProductRepository;
import az.edu.itbrains.shopper.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ProductDto> getFeaturedProducts() {
        List<Product> featuredProducts = productRepository.findByIsFeaturedTrue();
        return featuredProducts.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getTitle(),
                        product.getPrice(),
                        product.getImageUrl(),
                        product.isFeatured(),
                        product.getCreatedDate(),
                        product.getProductCode(),
                        product.getRewardPoints(),
                        product.getAvailability(),

                        new CategoryDto( // Category obyektindən CategoryDto yarat
                                product.getCategory().getId(),
                                product.getCategory().getName()


                        ),
                        new BrandDto(
                                product.getBrand().getId(),
                                product.getBrand().getName(),
                                product.getBrand().getImageUrl()
                        )
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getLatestProducts() {
        List<Product> latestProducts = productRepository.findByIsFeaturedFalseOrderByCreatedDateDesc();
        return latestProducts.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getTitle(),
                        product.getPrice(),
                        product.getImageUrl(),
                        product.isFeatured(),
                        product.getCreatedDate(),
                        product.getProductCode(),
                        product.getRewardPoints(),
                        product.getAvailability(),

                        new CategoryDto( // Category obyektindən CategoryDto yarat
                                product.getCategory().getId(),
                                product.getCategory().getName()
                        ),

                        new BrandDto(
                                product.getBrand().getId(),
                                product.getBrand().getName(),
                                product.getBrand().getImageUrl()
                        )
                ))
                .collect(Collectors.toList());
}

    @Override
    public ProductDetailDto getProductDetail(Long id) {
        Product product=productRepository.findById(id).orElseThrow();
        ProductDetailDto productDetailDto=modelMapper.map(product,ProductDetailDto.class);
        return productDetailDto;
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream().map(product -> {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setTitle(product.getTitle());
            dto.setPrice(product.getPrice());

            dto.setImageUrl(product.getImageUrl());
            // digər sahələr...
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByBrandId(Long brandId) {
        return productRepository.findByBrandId(brandId);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }


}

