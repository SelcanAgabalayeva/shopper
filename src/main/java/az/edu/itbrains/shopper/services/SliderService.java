package az.edu.itbrains.shopper.services;

import az.edu.itbrains.shopper.dtos.slider.*;

import java.util.List;

public interface SliderService {
    List<SliderDto> getSlider();

    List<SliderDashboardDto> getSliderAll();

    void createSlider(SliderCreateDto sliderCreateDto);

    SliderGetIdDto sliderGetIdDto(Long id);

    void updateSlider(SliderUpdateDto sliderUpdateDto, Long id);

}
