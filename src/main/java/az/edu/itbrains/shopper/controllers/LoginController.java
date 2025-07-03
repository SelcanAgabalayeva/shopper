package az.edu.itbrains.shopper.controllers;

import az.edu.itbrains.shopper.dtos.RegisterDto;
import az.edu.itbrains.shopper.dtos.slider.SliderDto;
import az.edu.itbrains.shopper.services.SliderService;
import az.edu.itbrains.shopper.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final SliderService sliderService;
    private final UserService userService;
    @GetMapping("/register")
    public String register(Model model) {
        List<SliderDto> sliderDtoList=sliderService.getSlider();
        model.addAttribute("sliders",sliderDtoList);
        return "register.html";
    }
    @PostMapping("/register")
    public String register(RegisterDto registerDto) {
        boolean result=userService.registerUser(registerDto);
        if(result){
            return "redirect:/register";
        }
        return "redirect:/register";

    }
    @GetMapping("/login")
    public String login() {
        return "register.html";

    }


}

