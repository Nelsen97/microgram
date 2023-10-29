package kg.nail.hw50.controller;

import kg.nail.hw50.dto.CommentDTO;
import kg.nail.hw50.exception.CommentNotFoundException;
import kg.nail.hw50.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{id}")
    public ResponseEntity<String> addComment(@PathVariable("id") Long postId, @RequestBody CommentDTO commentDTO) {
        try {
            commentService.addComment(postId, commentDTO);
            return new ResponseEntity<>(String.format("Комментарий к посту id = %s добавлен с id = %s",
                    postId, commentDTO.getId()), HttpStatus.OK);
        } catch (CommentNotFoundException c) {
            log.error("", c);
            return new ResponseEntity<>(c.getMessage(), c.getHttpStatus());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id) {
        try {
            commentService.deleteComment(id);
            return new ResponseEntity<>(String.format("Коммент с id = %s успешно удален", id), HttpStatus.OK);
        } catch (CommentNotFoundException c) {
            log.error("", c);
            return new ResponseEntity<>(c.getMessage(), c.getHttpStatus());
        }
    }
}
