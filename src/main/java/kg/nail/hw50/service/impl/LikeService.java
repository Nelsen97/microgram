package kg.nail.hw50.service.impl;

import kg.nail.hw50.dto.LikeDTO;

public interface LikeService {
    void addLike(Long postId, LikeDTO likeDTO);
    void deleteLike(Long id);
}
