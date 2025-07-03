package az.edu.itbrains.shopper.controllers.dashboard;

import az.edu.itbrains.shopper.dtos.product.ProductUpdateDto;
import az.edu.itbrains.shopper.dtos.slider.SliderCreateDto;
import az.edu.itbrains.shopper.dtos.slider.SliderDashboardDto;
import az.edu.itbrains.shopper.dtos.slider.SliderGetIdDto;
import az.edu.itbrains.shopper.dtos.slider.SliderUpdateDto;
import az.edu.itbrains.shopper.services.SliderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SliderController {
    private final SliderService sliderService;

    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }

    @GetMapping("/admin/slider")
    public String getAll(Model model) {
        List<SliderDashboardDto> allSlider= sliderService.getSliderAll();
        model.addAttribute("sliders", allSlider);
        return "/dashboard/slider/index";
    }
    @GetMapping("/admin/slider/create")
    public String create(Model model) {
        return "/dashboard/slider/create";
    }

    @PostMapping("/admin/slider/create")
    public String create(@ModelAttribute("slider") SliderCreateDto sliderCreateDto) {
        sliderService.createSlider(sliderCreateDto);
        return "redirect:/admin/slider";

    }
    @GetMapping("/admin/slider/update/{id}")
    public String updateSlider(@PathVariable("id") Long id, Model model) {
        SliderGetIdDto sliderGetIdDto = sliderService.sliderGetIdDto(id);
        model.addAttribute("slider", sliderGetIdDto);
        return "/dashboard/slider/update";
    }

    @PostMapping("/admin/slider/update/{id}")
    public String updateSlider(@ModelAttribute SliderUpdateDto sliderUpdateDto, @PathVariable("id") Long id) {
        sliderService.updateSlider(sliderUpdateDto,id);
        return "redirect:/admin/slider";

    }



}
