package kg.nail.hw50.service;

import kg.nail.hw50.dto.PostDTO;

import java.io.IOException;

public interface PostService {
    void createPost(PostDTO post) throws IOException;
    void deletePost(Long id);

}
