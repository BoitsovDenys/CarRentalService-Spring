package ua.training.car_rental.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.training.car_rental.entity.User;
import ua.training.car_rental.repository.UserRepository;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private PasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User '%s' not found", login)));

        System.out.println(user);
        System.out.println(user.isActive());
        System.out.println(getUserDetailsFromUser(user));
        System.out.println(encoder.encode("123"));

        return getUserDetailsFromUser(user);
    }

    private UserDetails getUserDetailsFromUser(User user) {
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .disabled(!user.isActive())
                .accountExpired(!user.isActive())
                .accountLocked(!user.isActive())
                .credentialsExpired(!user.isActive())
                .roles(user.getRole().name())
                .build();
    }
}
