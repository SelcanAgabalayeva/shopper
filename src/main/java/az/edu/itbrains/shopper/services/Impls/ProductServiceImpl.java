package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.brand.BrandDto;
import az.edu.itbrains.shopper.dtos.category.CategoryDto;
import az.edu.itbrains.shopper.dtos.product.*;
import az.edu.itbrains.shopper.models.Brand;
import az.edu.itbrains.shopper.models.Category;
import az.edu.itbrains.shopper.models.products.Product;
import az.edu.itbrains.shopper.repositories.BrandRepository;
import az.edu.itbrains.shopper.repositories.CategoryRepository;
import az.edu.itbrains.shopper.repositories.ProductRepository;
import az.edu.itbrains.shopper.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
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
    public List<Product> findAll() {

            return productRepository.findAll();


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

    @Override
    public List<ProductRelatedDto> getProductRelated() {
        List<Product> products=productRepository.findAll();
        List<ProductRelatedDto> relatedDtos=products.stream().map(product -> modelMapper.map(product, ProductRelatedDto.class)).collect(Collectors.toList());
        return relatedDtos;
    }

    @Override
    public List<ProductDashboardDto> getProductAll() {
        List<ProductDashboardDto> result=productRepository.findAll().stream().map(products->modelMapper.map(products,ProductDashboardDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public void createProduct(ProductCreateDto productCreateDto) {

        Product product=new Product();
        product.setTitle(productCreateDto.getTitle());
        product.setPrice(productCreateDto.getPrice());
        product.setProductCode(productCreateDto.getProductCode());
        product.setRewardPoints(productCreateDto.getRewardPoints());
        product.setAvailability(productCreateDto.getAvailability());
        product.setImageUrl(productCreateDto.getImageUrl());
        product.setFeatured(productCreateDto.isFeatured());
        Brand brand = brandRepository.findById(productCreateDto.getBrandId()) // <-- Buradakı dto
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        product.setBrand(brand);
        Category category = categoryRepository.findById(productCreateDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        productRepository.save(product);

    }

    @Override
    public ProductUpdateDto getUpdateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductUpdateDto dto = new ProductUpdateDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setProductCode(product.getProductCode());
        dto.setRewardPoints(product.getRewardPoints());
        dto.setAvailability(product.getAvailability());
        dto.setImageUrl(product.getImageUrl());
        dto.setFeatured(product.isFeatured());
        dto.setBrandId(product.getBrand().getId());
        dto.setCategoryId(product.getCategory().getId());

        return dto;
    }

    @Override
    public void updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setTitle(productUpdateDto.getTitle());
        product.setPrice(productUpdateDto.getPrice());
        product.setProductCode(productUpdateDto.getProductCode());
        product.setRewardPoints(productUpdateDto.getRewardPoints());
        product.setAvailability(productUpdateDto.getAvailability());
        product.setImageUrl(productUpdateDto.getImageUrl());
        product.setFeatured(productUpdateDto.isFeatured());

        Brand brand = brandRepository.findById(productUpdateDto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        product.setBrand(brand);

        Category category = categoryRepository.findById(productUpdateDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product findProduct =productRepository.findById(id).orElseThrow();
        productRepository.delete(findProduct);
    }
}

