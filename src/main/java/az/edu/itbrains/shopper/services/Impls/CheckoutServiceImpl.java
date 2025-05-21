package az.edu.itbrains.shopper.services.Impls;

import az.edu.itbrains.shopper.dtos.checkout.*;
import az.edu.itbrains.shopper.models.Checkout;
import az.edu.itbrains.shopper.repositories.CheckoutRepository;
import az.edu.itbrains.shopper.services.CheckoutService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final ModelMapper modelMapper;

    public CheckoutServiceImpl(CheckoutRepository checkoutRepository, ModelMapper modelMapper) {
        this.checkoutRepository = checkoutRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCheckout(CheckoutDto checkoutDto) {
        Checkout checkout=new Checkout();
        checkout.setFirstName(checkoutDto.getFirstName());
        checkout.setLastName(checkoutDto.getLastName());
        checkout.setEmail(checkoutDto.getEmail());
        checkout.setTelephone(checkoutDto.getTelephone());
        checkout.setFax(checkoutDto.getFax());
        checkout.setCompany(checkoutDto.getCompany());
        checkout.setCompanyId(checkoutDto.getCompanyId());
        checkout.setAddress1(checkoutDto.getAddress1());
        checkout.setAddress2(checkoutDto.getAddress2());
        checkout.setCity(checkoutDto.getCity());
        checkout.setPostCode(checkoutDto.getPostCode());
        checkout.setCountry(checkoutDto.getCountry());
        checkout.setRegion(checkoutDto.getRegion());
        checkout.setComment(checkoutDto.getComment());
        checkoutRepository.save(checkout);
    }

    @Override
    public List<CheckoutDashboardDto> getCheckoutAll() {
        List<CheckoutDashboardDto> results=checkoutRepository.findAll().stream().map(checkouts->modelMapper.map(checkouts, CheckoutDashboardDto.class)).collect(Collectors.toList());
        return results;
    }

    @Override
    public void createCheckout(CheckoutCreateDto checkoutCreateDto) {
        Checkout checkout=new Checkout();
        checkout.setFirstName(checkoutCreateDto.getFirstName());
        checkout.setLastName(checkoutCreateDto.getLastName());
        checkout.setEmail(checkoutCreateDto.getEmail());
        checkout.setTelephone(checkoutCreateDto.getTelephone());
        checkout.setFax(checkoutCreateDto.getFax());
        checkout.setCompany(checkoutCreateDto.getCompany());
        checkout.setCompanyId(checkoutCreateDto.getCompanyId());
        checkout.setAddress1(checkoutCreateDto.getAddress1());
        checkout.setAddress2(checkoutCreateDto.getAddress2());
        checkout.setCity(checkoutCreateDto.getCity());
        checkout.setPostCode(checkoutCreateDto.getPostCode());
        checkout.setCountry(checkoutCreateDto.getCountry());
        checkout.setRegion(checkoutCreateDto.getRegion());
        checkout.setComment(checkoutCreateDto.getComment());

        checkoutRepository.save(checkout);

    }

    @Override
    public CheckoutGetIdDto checkoutGetIdDto(Long id) {
        Checkout  checkout=checkoutRepository.findById(id).orElseThrow();
        CheckoutGetIdDto checkoutGetIdDto=modelMapper.map(checkout,CheckoutGetIdDto.class);
        return checkoutGetIdDto;

    }

    @Override
    public void updateCheckout(CheckoutUpdateDto checkoutUpdateDto, Long id) {
        Checkout checkout=new Checkout();
        checkout.setId(checkoutUpdateDto.getId());
        checkout.setFirstName(checkoutUpdateDto.getFirstName());
        checkout.setLastName(checkoutUpdateDto.getLastName());
        checkout.setEmail(checkoutUpdateDto.getEmail());
        checkout.setTelephone(checkoutUpdateDto.getTelephone());
        checkout.setFax(checkoutUpdateDto.getFax());
        checkout.setCompany(checkoutUpdateDto.getCompany());
        checkout.setCompanyId(checkoutUpdateDto.getCompanyId());
        checkout.setAddress1(checkoutUpdateDto.getAddress1());
        checkout.setAddress2(checkoutUpdateDto.getAddress2());
        checkout.setCity(checkoutUpdateDto.getCity());
        checkout.setPostCode(checkoutUpdateDto.getPostCode());
        checkout.setCountry(checkoutUpdateDto.getCountry());
        checkout.setRegion(checkoutUpdateDto.getRegion());
        checkout.setComment(checkoutUpdateDto.getComment());

        checkoutRepository.save(checkout);
    }

    @Override
    public void deleteCheckout(Long id) {
        Checkout checkout =checkoutRepository.findById(id).orElseThrow();
        checkoutRepository.delete(checkout);

    }


}
