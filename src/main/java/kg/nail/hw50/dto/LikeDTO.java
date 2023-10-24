package kg.nail.hw50.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeDTO {

    Long id;
    UserDTO user;
    PostDTO postDTO;
}
