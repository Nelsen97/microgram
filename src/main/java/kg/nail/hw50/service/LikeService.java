package kg.nail.hw50.service;

import kg.nail.hw50.security.UserDetailsImpl;

public interface LikeService {
    Long addLike(Long postId, UserDetailsImpl user);
}
