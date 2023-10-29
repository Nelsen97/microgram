package kg.nail.hw50.service.impl;

import kg.nail.hw50.dto.UserDTO;
import kg.nail.hw50.entity.User;
import kg.nail.hw50.exception.UserNotFoundException;
import kg.nail.hw50.repository.UserRepository;
import kg.nail.hw50.security.UserDetailsImpl;
import kg.nail.hw50.service.AuthDetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthDetailsServiceImpl implements AuthDetailsService {
    UserRepository userRepository;

    @Override
    public void registration(UserDTO userDTO) {

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User with email %s is not found".formatted(email),
                        HttpStatus.NOT_FOUND));
        return new UserDetailsImpl(user);
    }
}
