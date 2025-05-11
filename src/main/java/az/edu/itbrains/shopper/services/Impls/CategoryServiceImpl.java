package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.CategoryNavbarDto;
import az.edu.itbrains.shopper.models.Category;
import az.edu.itbrains.shopper.repositories.CategoryRepository;
import az.edu.itbrains.shopper.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
