package kg.nail.hw50.service.impl;

import kg.nail.hw50.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findUserByEmail(String email);
    Optional<UserDTO> findUserByUsername(String username);
    Optional<UserDTO> findUserByUsernameLike(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    boolean existsUserById(Long id);

}
