package kg.nail.hw50.controller;

import kg.nail.hw50.dto.UserDTO;
import kg.nail.hw50.exception.CustomException;
import kg.nail.hw50.exception.UserNotFoundException;
import kg.nail.hw50.service.UserService;
import kg.nail.hw50.service.impl.AuthDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final AuthDetailsServiceImpl authDetailsService;

    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam String search) {
        try {
            return new ResponseEntity<>(userService.findUserByUsernameLike(search), HttpStatus.OK);
        } catch (UserNotFoundException u) {
            log.error("", u);
            return new ResponseEntity<>(u.getMessage(), u.getHttpStatus());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
        try {
            authDetailsService.registration(user);
            return new ResponseEntity<>("Пользователь успешно зарегистрирован!", HttpStatus.OK);
        } catch (CustomException e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPostsByUserId(@RequestParam Long userId) {
        try {
            return new ResponseEntity<>(userService.getAllPostsByUserId(userId), HttpStatus.OK);
        } catch (CustomException e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }

}
