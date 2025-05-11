package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.FeatureBoxDto;
import az.edu.itbrains.shopper.dtos.SliderDto;
import az.edu.itbrains.shopper.models.Slider;
import az.edu.itbrains.shopper.repositories.FeatureBoxRepository;
import az.edu.itbrains.shopper.services.FeatureBoxService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureBoxServiceImpl implements FeatureBoxService {
    private final ModelMapper modelMapper;
    private final FeatureBoxRepository featureBoxRepository;

    public FeatureBoxServiceImpl(ModelMapper modelMapper, FeatureBoxRepository featureBoxRepository) {
        this.modelMapper = modelMapper;
        this.featureBoxRepository = featureBoxRepository;
    }

    @Override
    public List<FeatureBoxDto> getAllFeatureBox() {
        List<FeatureBoxDto> getAllFeature=featureBoxRepository.findAll().stream().map(box -> modelMapper.map(box,FeatureBoxDto.class)).collect(Collectors.toList());
        return getAllFeature;
    }

}
