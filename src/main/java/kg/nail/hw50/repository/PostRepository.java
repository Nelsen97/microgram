package kg.nail.hw50.repository;

import kg.nail.hw50.entity.Post;
import kg.nail.hw50.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User userDTO);
    List<Post> findByUserIn(List<User> followingUserDTOS);
}
