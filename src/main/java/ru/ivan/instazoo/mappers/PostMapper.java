package ru.ivan.instazoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.ivan.instazoo.dto.PostDTO;
import ru.ivan.instazoo.entities.User;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDTO toDTO(User user);

}
