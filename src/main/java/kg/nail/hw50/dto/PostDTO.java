package kg.nail.hw50.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDTO {

    Long id;
    MultipartFile file;
    String description;
    LocalDateTime publicationTime = LocalDateTime.now();
    UserDTO user;

}
