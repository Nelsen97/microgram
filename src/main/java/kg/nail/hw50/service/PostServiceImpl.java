package kg.nail.hw50.service;

import kg.nail.hw50.dto.PostDTO;
import kg.nail.hw50.entity.User;
import kg.nail.hw50.exception.FileNotFoundException;
import kg.nail.hw50.exception.PostNotFoundException;
import kg.nail.hw50.exception.UserNotFoundException;
import kg.nail.hw50.mapper.PostMapper;
import kg.nail.hw50.repository.PostRepository;
import kg.nail.hw50.repository.UserRepository;
import kg.nail.hw50.service.impl.PostService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void createPost(PostDTO postDTO) throws IOException {
        Optional<User> user = userRepository.findByEmail(postDTO.getUser().getEmail());
        if (user.isPresent()) {
            if (postDTO.getFile().isEmpty()) {
                throw new FileNotFoundException("Файл не найден!", HttpStatus.NOT_FOUND);
            }
            postRepository.save(postMapper.toPostEntity(postDTO));
        } else {
            throw new UserNotFoundException("Пользователь не найден!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deletePost(Long id) {
        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new PostNotFoundException("Пост не найден!", HttpStatus.NOT_FOUND);
        }
    }
}
