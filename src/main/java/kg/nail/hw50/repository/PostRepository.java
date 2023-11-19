package kg.nail.hw50.repository;

import kg.nail.hw50.entity.Post;
import kg.nail.hw50.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select p from Post p where p.user.id = :userId")
    List<Post> findPostsByUserId(Long userId);
    List<Post> findByUserIn(List<User> followingUser);
}
