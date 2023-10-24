package kg.nail.hw50.util;

import kg.nail.hw50.entity.Comment;
import kg.nail.hw50.entity.Like;
import kg.nail.hw50.entity.Post;
import kg.nail.hw50.entity.User;
import kg.nail.hw50.repository.CommentRepository;
import kg.nail.hw50.repository.LikeRepository;
import kg.nail.hw50.repository.PostRepository;
import kg.nail.hw50.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDatabase implements CommandLineRunner {
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        commentRepository.deleteAll();
        likeRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();

        List<Post> postList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            postList.add(Post.builder()
                    .description("Post " + i)
//                    .image(i + ".jpg")
                    .publicationTime(LocalDateTime.now())
                    .build());
        }

        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .username("Nail")
                .email("nail@gmail.com")
                .posts(postList)
                .build());
        users.add(User.builder()
                .username("Alfit")
                .email("alfit@gmail.com")
                .posts(postList)
                .build());
        users.add(User.builder()
                .username("Tima")
                .email("tima@gmail.com")
                .posts(postList)
                .build());

        List<Comment> comments = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            comments.add(Comment.builder()
                    .commentaryText("This is " + i + " comment")
                    .commentaryTime(LocalDateTime.now())
                    .post(postList.get(0))
                    .user(users.get(0))
                    .build());
        }

        List<Like> likes = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            likes.add(Like
                    .builder()
//                    (LocalDateTime.now())
                    .postLiked(postList.get(1))
                    .user(users.get(1))
                    .build());
        }
        postRepository.saveAll(postList);
        userRepository.saveAll(users);
        commentRepository.saveAll(comments);
        likeRepository.saveAll(likes);

    }
}
