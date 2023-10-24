package kg.nail.hw50.service;

import kg.nail.hw50.dto.CommentDTO;
import kg.nail.hw50.entity.Comment;
import kg.nail.hw50.entity.Post;
import kg.nail.hw50.entity.User;
import kg.nail.hw50.exception.CommentNotFoundException;
import kg.nail.hw50.exception.PostNotFoundException;
import kg.nail.hw50.exception.UserNotFoundException;
import kg.nail.hw50.repository.CommentRepository;
import kg.nail.hw50.repository.PostRepository;
import kg.nail.hw50.repository.UserRepository;
import kg.nail.hw50.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public void addComment(Long postId, CommentDTO comment) {
        Optional<Post> post = postRepository.findById(postId);
        Optional<User> user = userRepository.findByEmail(comment.getUserDTO().getEmail());
        if (post.isPresent()) {
            if (user.isPresent()) {
                commentRepository.save(Comment.builder()
                        .user(user.get())
                        .post(post.get())
                        .commentaryTime(LocalDateTime.now())
                        .commentaryText(comment.getCommentaryText())
                        .build());
            } else {
                throw new UserNotFoundException("Такого пользователя не существует!", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new PostNotFoundException("Такого поста не существует", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new CommentNotFoundException("Коммент не найден!", HttpStatus.NOT_FOUND);
        }
    }
}
