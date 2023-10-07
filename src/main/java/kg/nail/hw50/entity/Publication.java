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
public class Publication {
    private String photo;
    private String description;
    private LocalDateTime publicationTime;
}
