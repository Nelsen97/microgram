package kg.nail.hw50.controller;

import kg.nail.hw50.exception.UserNotFoundException;
import kg.nail.hw50.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<String> searchUser(@RequestParam String search) {
        try {
            userService.findUserByUsernameLike(search);
            return new ResponseEntity<>(
                    String.format("Пользователь с никнеймом = %s найден!",
                            search), HttpStatus.OK);
        } catch (UserNotFoundException u) {
            log.error("", u);
            return new ResponseEntity<>(u.getMessage(), u.getHttpStatus());
        }
    }

}
