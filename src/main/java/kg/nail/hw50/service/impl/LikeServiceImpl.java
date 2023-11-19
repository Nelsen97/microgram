package kg.nail.hw50.service.impl;

import kg.nail.hw50.entity.Like;
import kg.nail.hw50.entity.Post;
import kg.nail.hw50.exception.CustomException;
import kg.nail.hw50.repository.LikeRepository;
import kg.nail.hw50.repository.PostRepository;
import kg.nail.hw50.security.UserDetailsImpl;
import kg.nail.hw50.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeServiceImpl implements LikeService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Override
    public Long addLike(Long postId, UserDetailsImpl user) throws CustomException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException("Нет такого поста!", HttpStatus.NOT_FOUND)
        );

        Optional<Like> likedPost = likeRepository.findByUserAndPost(user.getUser(), post);

        if (likedPost.isEmpty()) {
            Like like = likeRepository.save(
                    Like.builder()
                            .post(post)
                            .user(user.getUser())
                            .build());
            log.info("Пользователь - %s поставил лайк посту - %s".formatted(user.getUser().getUsername(), post.getId()));
            return like.getId();
        } else {
            throw new CustomException("Вы уже ставили лайк под этим постом", HttpStatus.BAD_REQUEST);
        }
    }
}
