package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.brand.BrandCreateDto;
import az.edu.itbrains.shopper.dtos.brand.BrandDashboardDto;
import az.edu.itbrains.shopper.dtos.brand.BrandDto;
import az.edu.itbrains.shopper.dtos.brand.BrandUpdateDto;
import az.edu.itbrains.shopper.models.Brand;
import az.edu.itbrains.shopper.repositories.BrandRepository;
import az.edu.itbrains.shopper.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(brand -> new BrandDto(brand.getId(), brand.getName(),brand.getImageUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BrandDashboardDto> getDashboardBrands() {
        List<Brand> brandList=brandRepository.findAll();
        List<BrandDashboardDto> brandDashboardDtoList=brandList.stream().map(x->modelMapper.map(x, BrandDashboardDto.class)).collect(Collectors.toList());
        return brandDashboardDtoList;
    }

    @Override
    public void createBrand(BrandCreateDto brandCreateDto) {
        Brand brand = new Brand();
        brand.setName(brandCreateDto.getName());
        brand.setImageUrl(brandCreateDto.getImageUrl());
        brandRepository.save(brand);
    }

    @Override
    public BrandUpdateDto getUpdateBrand(Long id) {
        Brand brand=brandRepository.findById(id).orElseThrow();
        BrandUpdateDto brandUpdateDto=modelMapper.map(brand,BrandUpdateDto.class);
        return brandUpdateDto;
    }

    @Override
    public void updateBrand(Long id, BrandUpdateDto brandUpdateDto) {
        Brand brand=brandRepository.findById(id).orElseThrow();
        brand.setName(brandUpdateDto.getName());
        brand.setImageUrl(brandUpdateDto.getImageUrl());
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        Brand findBrand=brandRepository.findById(id).orElseThrow();
        brandRepository.delete(findBrand);

    }
}
