package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.slider.*;
import az.edu.itbrains.shopper.models.Slider;
import az.edu.itbrains.shopper.repositories.SliderRepository;
import az.edu.itbrains.shopper.services.SliderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    @Override
    public List<SliderDashboardDto> getSliderAll() {
        List<SliderDashboardDto> result=sliderRepository.findAll().stream().map(sliders->modelMapper.map(sliders,SliderDashboardDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public void createSlider(SliderCreateDto sliderCreateDto) {
        Slider slider=new Slider();
        slider.setImageUrl(sliderCreateDto.getImageUrl());
        slider.setTitle(sliderCreateDto.getTitle());
        slider.setSubTitle(sliderCreateDto.getSubtitle());
        slider.setDescription(sliderCreateDto.getDescription());

        sliderRepository.save(slider);

    }

    @Override
    public SliderGetIdDto sliderGetIdDto(Long id) {
        Slider slider=sliderRepository.findById(id).orElseThrow();
        SliderGetIdDto sliderGetIdDto=modelMapper.map(slider,SliderGetIdDto.class);
        return sliderGetIdDto;
    }

    @Override
    public void updateSlider(SliderUpdateDto sliderUpdateDto, Long id) {
        Slider slider=new Slider();
        slider.setId(sliderUpdateDto.getId());
        slider.setImageUrl(sliderUpdateDto.getImageUrl());
        slider.setTitle(sliderUpdateDto.getTitle());
        slider.setSubTitle(sliderUpdateDto.getSubtitle());
        slider.setDescription(sliderUpdateDto.getDescription());

        sliderRepository.save(slider);
    }
}
