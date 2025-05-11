package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.BrandDto;
import az.edu.itbrains.shopper.dtos.FeatureBoxDto;
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
}
