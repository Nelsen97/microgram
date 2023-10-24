package kg.nail.hw50.service.impl;

import kg.nail.hw50.dto.CommentDTO;

public interface CommentService {
    void addComment(Long postId, CommentDTO comment);
    void deleteComment(Long id);

}
