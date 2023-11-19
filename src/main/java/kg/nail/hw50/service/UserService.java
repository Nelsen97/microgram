package kg.nail.hw50.service;

import kg.nail.hw50.dto.PostDTO;
import kg.nail.hw50.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findUserByEmail(String email);
    Optional<UserDTO> findUserByUsername(String username);
    UserDTO findUserByUsernameLike(String username);
    List<PostDTO> getAllPostsByUserId(Long userId);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    boolean existsUserById(Long id);
}
