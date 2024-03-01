package ru.ivan.instazoo.services;

import org.springframework.stereotype.Service;
import ru.ivan.instazoo.dto.CommentDTO;
import ru.ivan.instazoo.exceptions.AccessDeniedException;
import ru.ivan.instazoo.exceptions.CommentNotFoundException;
import ru.ivan.instazoo.exceptions.PostNotFoundException;
import ru.ivan.instazoo.exceptions.UserNotFoundException;

import java.security.Principal;
import java.util.List;

@Service
public interface CommentService {
    List<CommentDTO> getAllByPosts(Long postId) throws PostNotFoundException;

    CommentDTO save(CommentDTO commentDTO, Principal principal) throws PostNotFoundException, UserNotFoundException;

    void deleteById(Long id, Principal principal) throws CommentNotFoundException, AccessDeniedException;

}
