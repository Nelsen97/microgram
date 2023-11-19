package kg.nail.hw50.service.impl;

import kg.nail.hw50.dto.CommentDTO;
import kg.nail.hw50.entity.Comment;
import kg.nail.hw50.entity.Post;
import kg.nail.hw50.entity.User;
import kg.nail.hw50.exception.CustomException;
import kg.nail.hw50.mapper.CommentMapper;
import kg.nail.hw50.repository.CommentRepository;
import kg.nail.hw50.repository.PostRepository;
import kg.nail.hw50.security.UserDetailsImpl;
import kg.nail.hw50.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comment addComment(Long postId, CommentDTO comment, UserDetailsImpl user) throws CustomException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException("Поста с id - %s не существует!".formatted(postId), HttpStatus.NOT_FOUND)
        );

        if (!comment.getCommentaryText().isBlank()) {
            Comment commentEntity = commentMapper.toCommentEntity(comment);
            commentEntity.setUser(user.getUser());
            commentEntity.setPost(post);
            return commentRepository.save(commentEntity);
        } else {
            throw new CustomException("Комментарий не может быть пустым!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteComment(Long id, UserDetailsImpl user) throws CustomException {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException("Такого комментария не существует!", HttpStatus.NOT_FOUND)
        );
        User user1 = comment.getPost().getUser();
        User user2 = user.getUser();
        if (user1.getId().equals(user2.getId())) {
            commentRepository.deleteById(id);
        } else {
            throw new CustomException("Вы не можете удалить комментарии под чужой публикацией!",
                    HttpStatus.NOT_FOUND);
        }
    }
}
