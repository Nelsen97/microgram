package kg.nail.hw50.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @Builder.Default
    private Boolean isEnabled = true;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @ManyToMany
    @JoinTable (
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<User> following;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;
}
