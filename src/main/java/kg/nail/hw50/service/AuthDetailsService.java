package kg.nail.hw50.service;

import kg.nail.hw50.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthDetailsService extends UserDetailsService {

    void registration(UserDTO userDTO);

}
