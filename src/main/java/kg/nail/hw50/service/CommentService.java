package kg.nail.hw50.service;

import kg.nail.hw50.dto.CommentDTO;
import kg.nail.hw50.entity.Comment;
import kg.nail.hw50.security.UserDetailsImpl;

public interface CommentService {
    Comment addComment(Long postId, CommentDTO comment, UserDetailsImpl user);
    void deleteComment(Long id, UserDetailsImpl user);

}
