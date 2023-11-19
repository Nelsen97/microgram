package kg.nail.hw50.controller;

import kg.nail.hw50.exception.CustomException;
import kg.nail.hw50.security.UserDetailsImpl;
import kg.nail.hw50.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
@Slf4j
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/add/{postId}")
    public ResponseEntity<?> addLike(@PathVariable("postId") Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return new ResponseEntity<>(likeService.addLike(id, user), HttpStatus.CREATED);
        } catch (CustomException e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }
    }
}
