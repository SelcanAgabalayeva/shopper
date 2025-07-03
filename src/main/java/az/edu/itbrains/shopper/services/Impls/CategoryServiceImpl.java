package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.category.CategoryCreateDto;
import az.edu.itbrains.shopper.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.shopper.dtos.category.CategoryNavbarDto;
import az.edu.itbrains.shopper.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.shopper.models.Category;
import az.edu.itbrains.shopper.repositories.CategoryRepository;
import az.edu.itbrains.shopper.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CategoryNavbarDto> getNavbarCategories() {
        List<Category> categoryList=categoryRepository.findByIsNavbarTrue();
        List<CategoryNavbarDto> categoryNavbarDtoList=categoryList.stream().map(category -> modelMapper.map(category, CategoryNavbarDto.class)).collect(Collectors.toList());

        return categoryNavbarDtoList;
    }

    @Override
    public List<CategoryDashboardDto> getDashboardCategories() {
        return categoryRepository
                .findAll(Sort.by(Sort.Direction.ASC, "id"))   //
                .stream()
                .map(cat -> modelMapper.map(cat, CategoryDashboardDto.class))
                .toList();
    }

    @Override
    public void createCategory(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setName(categoryCreateDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public CategoryUpdateDto getUpdateCategory(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow();
        CategoryUpdateDto categoryUpdateDto=modelMapper.map(category,CategoryUpdateDto.class);
        return categoryUpdateDto;
    }

    @Override
    public void updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
        Category category=categoryRepository.findById(id).orElseThrow();
        category.setName(categoryUpdateDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category findCategory=categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(findCategory);

    }
}
