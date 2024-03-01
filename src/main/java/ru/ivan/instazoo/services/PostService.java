package ru.ivan.instazoo.services;

import org.springframework.stereotype.Service;
import ru.ivan.instazoo.dto.PostDTO;
import ru.ivan.instazoo.exceptions.AccessDeniedException;
import ru.ivan.instazoo.exceptions.PostNotFoundException;
import ru.ivan.instazoo.exceptions.UserNotFoundException;

import java.security.Principal;
import java.util.List;

@Service
public interface PostService {
    List<PostDTO> getAllPosts();

    PostDTO postById(Long id) throws PostNotFoundException;
    PostDTO save(PostDTO postDTO, Principal principal) throws UserNotFoundException;

    void deleteById(Long id, Principal principal) throws PostNotFoundException, AccessDeniedException;

}
