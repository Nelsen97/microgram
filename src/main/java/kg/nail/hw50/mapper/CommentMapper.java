package kg.nail.hw50.mapper;

import kg.nail.hw50.dto.CommentDTO;
import kg.nail.hw50.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    public Comment toCommentEntity(CommentDTO comment) {
        return Comment.builder()
                .commentaryText(comment.getCommentaryText())
                .commentaryTime(LocalDateTime.now())
                .build();
    }

    public CommentDTO toCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .commentaryText(comment.getCommentaryText())
                .commentaryTime(LocalDateTime.now())
                .build();
    }

}
