package kg.nail.hw50.service.impl;

import kg.nail.hw50.dto.LikeDTO;
import kg.nail.hw50.entity.Like;
import kg.nail.hw50.entity.Post;
import kg.nail.hw50.entity.User;
import kg.nail.hw50.exception.LikeAlreadyExistException;
import kg.nail.hw50.exception.LikeNotFoundException;
import kg.nail.hw50.exception.PostNotFoundException;
import kg.nail.hw50.exception.UserNotFoundException;
import kg.nail.hw50.repository.LikeRepository;
import kg.nail.hw50.repository.PostRepository;
import kg.nail.hw50.repository.UserRepository;
import kg.nail.hw50.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Override
    public void addLike(Long postId, LikeDTO likeDTO) {
        Optional<Post> post = postRepository.findById(postId);
        Optional<User> user = userRepository.findByEmail(likeDTO.getUser().getEmail());
        if (post.isPresent()) {
            if (user.isPresent()) {
                if (!likeRepository.existsByUserAndPostLiked(user.get(), post.get())) {
                    likeRepository.save(Like.builder()
                            .postLiked(post.get())
                            .user(user.get())
                            .build());
                } else {
                    throw new LikeAlreadyExistException(
                            String.format("Лайк под постом id = %s уже был поставлен пользователем id = %s",
                                    post.get().getId(), user.get().getId()), HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new UserNotFoundException("Такого пользователя не существует!", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new PostNotFoundException("Такого поста не существует", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteLike(Long id) {
        if (likeRepository.existsById(id)) {
            likeRepository.deleteById(id);
        } else {
            throw new LikeNotFoundException("Пользователь еще не ставил лайка под этим постом!",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
