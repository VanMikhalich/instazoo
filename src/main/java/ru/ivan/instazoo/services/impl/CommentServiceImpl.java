package ru.ivan.instazoo.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ivan.instazoo.dto.CommentDTO;
import ru.ivan.instazoo.entities.Comment;
import ru.ivan.instazoo.entities.Post;
import ru.ivan.instazoo.entities.User;
import ru.ivan.instazoo.exceptions.AccessDeniedException;
import ru.ivan.instazoo.exceptions.CommentNotFoundException;
import ru.ivan.instazoo.exceptions.PostNotFoundException;
import ru.ivan.instazoo.exceptions.UserNotFoundException;
import ru.ivan.instazoo.mappers.CommentMapper;
import ru.ivan.instazoo.repositories.CommentRepository;
import ru.ivan.instazoo.repositories.PostRepository;
import ru.ivan.instazoo.repositories.UserRepository;
import ru.ivan.instazoo.services.CommentService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public List<CommentDTO> getAllByPosts(Long postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
        return post.getComments()
                .stream()
                .map(CommentMapper.INSTANCE::toCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO, Principal principal) throws PostNotFoundException, UserNotFoundException {
        User user = getUserByPrincipal(principal);
        Comment commentToDb = CommentMapper.INSTANCE.toComment(commentDTO);
        commentToDb.setUser(user);
        return CommentMapper.INSTANCE.toCommentDTO(commentRepository.save(commentToDb));
    }

    @Override
    public void deleteById(Long id, Principal principal) throws CommentNotFoundException, AccessDeniedException {
        Comment comment = getCommentEntityById(id);
        if (comment.getUser().getUsername().equals(principal.getName())){
            commentRepository.delete(comment);
        }
        else throw new AccessDeniedException("You haven`t access to delete Comment");
    }

    private User getUserByPrincipal(Principal principal) throws UserNotFoundException {
        String username = principal.getName();
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
    }

    private Comment getCommentEntityById(Long id) throws CommentNotFoundException {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
    }
}
