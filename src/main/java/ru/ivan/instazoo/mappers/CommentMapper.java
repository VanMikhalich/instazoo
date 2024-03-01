package ru.ivan.instazoo.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.instazoo.dto.CommentDTO;
import ru.ivan.instazoo.entities.Comment;
import ru.ivan.instazoo.entities.Post;
import ru.ivan.instazoo.entities.User;
import ru.ivan.instazoo.exceptions.PostNotFoundException;
import ru.ivan.instazoo.exceptions.UserNotFoundException;
import ru.ivan.instazoo.repositories.PostRepository;
import ru.ivan.instazoo.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment toComment(CommentDTO commentDTO) throws PostNotFoundException, UserNotFoundException {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        comment.setCreationTime(commentDTO.getCreationTime());
        comment.setPost(getPostById(commentDTO.getPostId()));
        comment.setUser(getUserByUsername(commentDTO.getAuthor()));
        return comment;
    }

    public CommentDTO toCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .creationTime(comment.getCreationTime())
                .postId(comment.getPost().getId())
                .author(comment.getUser().getUsername())
                .build();
    }

    private Post getPostById(Long id) throws PostNotFoundException {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            throw new PostNotFoundException("Post not found with id: " + id);
        }
        return post;
    }

    private User getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null){
            throw new UserNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}
