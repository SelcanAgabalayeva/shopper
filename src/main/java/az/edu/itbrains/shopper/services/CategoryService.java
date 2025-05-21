package az.edu.itbrains.shopper.services;

import az.edu.itbrains.shopper.dtos.category.CategoryCreateDto;
import az.edu.itbrains.shopper.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.shopper.dtos.category.CategoryNavbarDto;
import az.edu.itbrains.shopper.dtos.category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    List<CategoryNavbarDto> getNavbarCategories();

    List<CategoryDashboardDto> getDashboardCategories();

    void createCategory(CategoryCreateDto categoryCreateDto);

    CategoryUpdateDto getUpdateCategory(Long id);

    void updateCategory(Long id, CategoryUpdateDto categoryUpdateDto);

    void deleteCategory(Long id);

}
