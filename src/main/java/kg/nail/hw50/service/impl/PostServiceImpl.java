package kg.nail.hw50.service.impl;

import kg.nail.hw50.dto.PostDTO;
import kg.nail.hw50.entity.Post;
import kg.nail.hw50.exception.CustomException;
import kg.nail.hw50.exception.PostNotFoundException;
import kg.nail.hw50.mapper.PostMapper;
import kg.nail.hw50.repository.PostRepository;
import kg.nail.hw50.security.UserDetailsImpl;
import kg.nail.hw50.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final ImageService imageService;

    @Override
    public Post createPost(PostDTO postDTO, UserDetailsImpl user) throws IOException {
        Post postEntity = postMapper.toPostEntity(postDTO);
        postEntity.setUser(user.getUser());

        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : postDTO.getImageFiles()) {
            String imageUrl = imageService.uploadImage(file);
            imageUrls.add(imageUrl);
        }

        postEntity.setImages(imageUrls);

        return postRepository.save(postEntity);
    }

    @Override
    public void deletePost(Long id, UserDetailsImpl user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException("Пост не найден!", HttpStatus.NOT_FOUND));
        if (post.getUser() == null) {
            throw new CustomException("У поста нет пользователя!", HttpStatus.BAD_REQUEST);
        }
        if (post.getUser().getId().equals(user.getUser().getId())) {
            postRepository.deleteById(id);
        } else {
            throw new CustomException("Вы не можете удалить чужой пост!", HttpStatus.BAD_REQUEST);
        }
    }
}
