package kg.nail.hw50.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    private User userLiked;
    private Publication publicationLiked;
    private Commentary commentaryLiked;
    private LocalDateTime likeTime;
}