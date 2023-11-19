package kg.nail.hw50.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDTO {

    Long id;
    List<MultipartFile> imageFiles;
    String description;
    Long userId;
    LocalDateTime publicationTime = LocalDateTime.now();
}
