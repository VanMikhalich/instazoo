package ru.ivan.instazoo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.ivan.instazoo.dto.CommentDTO;
import ru.ivan.instazoo.entities.Comment;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDTO toCommentDTO(Comment comment);
    Comment toComment(CommentDTO commentDTO);
}
