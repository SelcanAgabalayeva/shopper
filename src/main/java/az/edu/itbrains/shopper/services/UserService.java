package az.edu.itbrains.shopper.services;

import az.edu.itbrains.shopper.dtos.RegisterDto;
import az.edu.itbrains.shopper.models.User;

public interface UserService {
    boolean registerUser(RegisterDto registerDto);
    User findUser(String email);
}
