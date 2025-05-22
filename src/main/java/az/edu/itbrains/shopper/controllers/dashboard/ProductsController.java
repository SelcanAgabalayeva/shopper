package az.edu.itbrains.shopper.controllers.dashboard;

import az.edu.itbrains.shopper.dtos.brand.BrandDto;
import az.edu.itbrains.shopper.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.shopper.dtos.product.ProductCreateDto;
import az.edu.itbrains.shopper.dtos.product.ProductDashboardDto;
import az.edu.itbrains.shopper.dtos.product.ProductUpdateDto;
import az.edu.itbrains.shopper.services.BrandService;
import az.edu.itbrains.shopper.services.CategoryService;
import az.edu.itbrains.shopper.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductsController {
    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public ProductsController(ProductService productService, BrandService brandService, CategoryService categoryService) {
        this.productService = productService;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/product")
    public String getAll(Model model) {
        List<ProductDashboardDto> allProduct = productService.getProductAll();
        model.addAttribute("products", allProduct);
        return "/dashboard/product/index";
    }

    @GetMapping("/admin/product/create")
    public String create(Model model) {
        model.addAttribute("product", new ProductCreateDto());
        model.addAttribute("brands", brandService.getAllBrands()); // brands siyahısı form üçün
        model.addAttribute("categories", categoryService.getNavbarCategories());
        return "/dashboard/product/create";
    }


    @PostMapping("/admin/product/create")
    public String create(@ModelAttribute("product") ProductCreateDto productCreateDto) {
        productService.createProduct(productCreateDto);
        return "redirect:/admin/product";

    }
    @GetMapping("/admin/product/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        ProductUpdateDto productUpdateDto = productService.getUpdateProduct(id);
        List<CategoryDashboardDto> categoryDtoList = categoryService.getDashboardCategories();
        List<BrandDto> brandDtoList = brandService.getAllBrands();  // brendlər üçün əlavə

        model.addAttribute("categories", categoryDtoList);
        model.addAttribute("brands", brandDtoList);
        model.addAttribute("product", productUpdateDto);

        return "/dashboard/product/update";
    }

    @PostMapping("/admin/product/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute("product") ProductUpdateDto productUpdateDto) {
        productService.updateProduct(id, productUpdateDto);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        return  "/dashboard/product/delete.html";
    }
    @PostMapping("/admin/product/delete/{id}")
    public String mdelete(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }

}
