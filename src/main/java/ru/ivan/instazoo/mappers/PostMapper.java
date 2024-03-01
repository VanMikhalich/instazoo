package ru.ivan.instazoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.ivan.instazoo.dto.PostDTO;
import ru.ivan.instazoo.entities.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDTO toPostDTO(Post post);
    Post toPost(PostDTO postDTO);

}
