package kg.nail.hw50.controller;

import kg.nail.hw50.dto.CommentDTO;
import kg.nail.hw50.exception.CommentNotFoundException;
import kg.nail.hw50.exception.CustomException;
import kg.nail.hw50.security.UserDetailsImpl;
import kg.nail.hw50.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add/{id}")
    public ResponseEntity<String> addComment(@PathVariable("id") Long postId,
                                             @RequestBody CommentDTO commentDTO,
                                             @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            commentService.addComment(postId, commentDTO, user);
            return new ResponseEntity<>(String.format("Комментарий к посту id = %s, " +
                            "пользователем - %s добавлен",
                    postId, user.getUser().getUsername()), HttpStatus.OK);
        } catch (CustomException c) {
            log.error("", c);
            return new ResponseEntity<>(c.getMessage(), c.getHttpStatus());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id,
                                                @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            commentService.deleteComment(id, user);
            return new ResponseEntity<>(String.format("Коммент с id = %s успешно удален", id), HttpStatus.OK);
        } catch (CustomException c) {
            log.error("", c);
            return new ResponseEntity<>(c.getMessage(), c.getHttpStatus());
        }
    }
}
