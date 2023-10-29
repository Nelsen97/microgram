package kg.nail.hw50.controller;

import kg.nail.hw50.dto.PostDTO;
import kg.nail.hw50.exception.PostNotFoundException;
import kg.nail.hw50.exception.UserNotFoundException;
import kg.nail.hw50.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(
            @ModelAttribute PostDTO postDTO) {
        try {
            postService.createPost(postDTO);
            return new ResponseEntity<>(String.format("Пост с id = %s пользователем с id = %s создан!",
                    postDTO.getId(), postDTO.getUser()), HttpStatus.OK);
        } catch (UserNotFoundException u) {
            log.error("", u);
            return new ResponseEntity<>(u.getMessage(), u.getHttpStatus());
        } catch (IOException e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        try {
            postService.deletePost(id);
            return new ResponseEntity<>(String.format("Пост (id = %s) успешно удален!", id), HttpStatus.OK);
        } catch (PostNotFoundException p) {
            log.error("", p);
            return new ResponseEntity<>(p.getMessage(), p.getHttpStatus());
        }
    }
}
