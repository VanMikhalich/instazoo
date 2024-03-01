package ru.ivan.instazoo.mappers;

import org.springframework.stereotype.Component;
import ru.ivan.instazoo.dto.PostDTO;
import ru.ivan.instazoo.entities.Post;

@Component
public class PostMapperImpl {
    public PostDTO toPostDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .creationTime(post.getCreationTime())
                .build();
    }

    public Post toPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setCreationTime(postDTO.getCreationTime());
        return post;
    }
}
