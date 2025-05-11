package az.edu.itbrains.shopper.security;

import az.edu.itbrains.shopper.models.User;
import az.edu.itbrains.shopper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

@Configuration
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User findUser=userRepository.findByEmail(username);
            org.springframework.security.core.userdetails.User user=new org.springframework.security.core.userdetails.User(
                    findUser.getEmail(),
                    findUser.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    findUser.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
            );

            return user;
        }catch (Exception e){
            throw new UsernameNotFoundException(e.getMessage());

        }

    }
}
