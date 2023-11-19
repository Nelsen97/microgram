package kg.nail.hw50.service.impl;

import kg.nail.hw50.dto.UserDTO;
import kg.nail.hw50.entity.User;
import kg.nail.hw50.exception.CustomException;
import kg.nail.hw50.exception.UserNotFoundException;
import kg.nail.hw50.mapper.UserMapper;
import kg.nail.hw50.repository.UserRepository;
import kg.nail.hw50.security.UserDetailsImpl;
import kg.nail.hw50.service.AuthDetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthDetailsServiceImpl implements AuthDetailsService, UserDetailsService {
    UserRepository userRepository;
    UserMapper userMapper;
    final PasswordEncoder encoder;

    @Override
    public void registration(UserDTO userDTO) {
        String email = userDTO.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new CustomException("User with email %s already exists!".formatted(email), HttpStatus.BAD_REQUEST);
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userRepository.save(userMapper.toUserEntity(userDTO));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User with email %s is not found".formatted(email),
                        HttpStatus.NOT_FOUND));
        return new UserDetailsImpl(user);
    }
}
