package az.edu.itbrains.shopper.services;

import az.edu.itbrains.shopper.dtos.CategoryNavbarDto;

import java.util.List;

public interface CategoryService {
    List<CategoryNavbarDto> getNavbarCategories();
}
