package kg.nail.hw50.mapper;

import kg.nail.hw50.dto.PostDTO;
import kg.nail.hw50.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper {

    public Post toPostEntity(PostDTO postDTO) {
        return Post.builder()
                .description(postDTO.getDescription())
                .images(postDTO.getImageFiles().stream()
                        .map(MultipartFile::getOriginalFilename)
                        .collect(Collectors.toList()))
                .publicationTime(postDTO.getPublicationTime())
                .build();
    }

    public PostDTO toPostDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .description(post.getDescription())
                .publicationTime(post.getPublicationTime())
                .userId(post.getUser().getId())
                .build();
    }


}
