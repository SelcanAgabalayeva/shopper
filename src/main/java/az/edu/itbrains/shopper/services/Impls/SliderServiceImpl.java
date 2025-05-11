package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.SliderDto;
import az.edu.itbrains.shopper.models.Slider;
import az.edu.itbrains.shopper.repositories.SliderRepository;
import az.edu.itbrains.shopper.services.SliderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SliderServiceImpl implements SliderService {
    private final SliderRepository sliderRepository;
    private final ModelMapper modelMapper;



    public SliderServiceImpl(SliderRepository sliderRepository, ModelMapper modelMapper) {
        this.sliderRepository = sliderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SliderDto> getSlider() {
        List<Slider> sliderList=sliderRepository.findAll().stream().collect(Collectors.toList());
        List<SliderDto> sliderDtoList=sliderList.stream().map(slider -> modelMapper.map(slider, SliderDto.class)).collect(Collectors.toList());
        return sliderDtoList;
    }
}
