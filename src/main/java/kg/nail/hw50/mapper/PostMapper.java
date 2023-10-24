package kg.nail.hw50.mapper;

import kg.nail.hw50.dto.PostDTO;
import kg.nail.hw50.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final UserMapper userMapper;

    public Post toPostEntity(PostDTO postDTO) throws IOException {
        return Post.builder()
                .image(postDTO.getFile().getBytes())
                .description(postDTO.getDescription())
                .publicationTime(postDTO.getPublicationTime())
                .user(userMapper.toUserEntity(postDTO.getUser()))
                .build();
    }


}
