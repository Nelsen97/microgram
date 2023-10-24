package kg.nail.hw50.repository;

import kg.nail.hw50.entity.Like;
import kg.nail.hw50.entity.Post;
import kg.nail.hw50.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPostLiked(User user, Post post);
}
