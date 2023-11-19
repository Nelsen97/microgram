package kg.nail.hw50.service;

import kg.nail.hw50.dto.PostDTO;
import kg.nail.hw50.entity.Post;
import kg.nail.hw50.security.UserDetailsImpl;

import java.io.IOException;

public interface PostService {
    Post createPost(PostDTO post, UserDetailsImpl user) throws IOException;
    void deletePost(Long id, UserDetailsImpl user);

}
