package kg.nail.hw50.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDTO {

    Long id;
    String commentaryText;
    LocalDateTime commentaryTime;
    UserDTO userDTO;
//    PostDTO postDTO;
}
