package az.edu.itbrains.shopper.services;

import az.edu.itbrains.shopper.dtos.brand.BrandCreateDto;
import az.edu.itbrains.shopper.dtos.brand.BrandDashboardDto;
import az.edu.itbrains.shopper.dtos.brand.BrandDto;
import az.edu.itbrains.shopper.dtos.brand.BrandUpdateDto;

import java.util.List;

public interface BrandService {
    List<BrandDto> getAllBrands();

    List<BrandDashboardDto> getDashboardBrands();

    void createBrand(BrandCreateDto brandCreateDto);

    BrandUpdateDto getUpdateBrand(Long id);

    void updateBrand(Long id, BrandUpdateDto brandUpdateDto);

    void deleteBrand(Long id);
}
