package kg.nail.hw50.service.impl;

import kg.nail.hw50.dto.PostDTO;
import kg.nail.hw50.dto.UserDTO;
import kg.nail.hw50.entity.User;
import kg.nail.hw50.exception.UserNotFoundException;
import kg.nail.hw50.mapper.PostMapper;
import kg.nail.hw50.mapper.UserMapper;
import kg.nail.hw50.repository.PostRepository;
import kg.nail.hw50.repository.UserRepository;
import kg.nail.hw50.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.map(userMapper::toUserDTO);
        } else {
            throw new UserNotFoundException(
                    String.format("Пользователь с email = %s не найден!", email),
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<UserDTO> findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.map(userMapper::toUserDTO);
        } else {
            throw new UserNotFoundException(
                    String.format("Пользователь с никнеймом = %s не найден!", username),
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserDTO findUserByUsernameLike(String username) {
        return userRepository.findByEmailOrUsernameContainingIgnoreCase(username)
                .map(userMapper::toUserDTO)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("Пользователи с никнеймом = %s не найдены!", username),
                                HttpStatus.NOT_FOUND)
                );
    }

    @Override
    public List<PostDTO> getAllPostsByUserId(Long userId) {
        return postRepository.findPostsByUserId(userId).stream()
                .map(postMapper::toPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsUserById(Long id) {
        return userRepository.existsUserById(id);
    }
}
