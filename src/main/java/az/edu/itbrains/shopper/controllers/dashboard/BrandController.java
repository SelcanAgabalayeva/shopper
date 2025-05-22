package az.edu.itbrains.shopper.controllers.dashboard;

import az.edu.itbrains.shopper.dtos.brand.BrandCreateDto;
import az.edu.itbrains.shopper.dtos.brand.BrandDashboardDto;
import az.edu.itbrains.shopper.dtos.brand.BrandUpdateDto;
import az.edu.itbrains.shopper.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    @GetMapping("/admin/brand")
    public String index(Model model){
        List<BrandDashboardDto> brandDashboardDtoList=brandService.getDashboardBrands();
        model.addAttribute("brands",brandDashboardDtoList);
        return "/dashboard/brand/index.html";
    }
    @GetMapping("/admin/brand/create")
    public String create(){

        return "/dashboard/brand/create.html";
    }

    @PostMapping("/admin/brand/create")
    public String create(BrandCreateDto brandCreateDto){
        brandService.createBrand(brandCreateDto);
        return "redirect:/admin/brand";
    }
    @GetMapping("/admin/brand/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        BrandUpdateDto brandUpdateDto=brandService.getUpdateBrand(id);
        model.addAttribute("brand",brandUpdateDto);
        return "/dashboard/brand/update.html";
    }
    @PostMapping("/admin/brand/edit/{id}")
    public String edit(@PathVariable Long id, BrandUpdateDto brandUpdateDto){
        brandService.updateBrand(id,brandUpdateDto);
        return "redirect:/admin/brand";
    }
    @GetMapping("/admin/brand/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        return  "/dashboard/brand/delete.html";
    }
    @PostMapping("/admin/brand/delete/{id}")
    public String mdelete(@PathVariable("id") Long id) {
        brandService.deleteBrand(id);
        return "redirect:/admin/brand";
    }
}
