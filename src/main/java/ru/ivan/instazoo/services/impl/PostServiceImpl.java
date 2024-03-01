package ru.ivan.instazoo.services.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ivan.instazoo.dto.PostDTO;
import ru.ivan.instazoo.entities.Post;
import ru.ivan.instazoo.entities.User;
import ru.ivan.instazoo.exceptions.AccessDeniedException;
import ru.ivan.instazoo.exceptions.PostNotFoundException;
import ru.ivan.instazoo.exceptions.UserNotFoundException;
import ru.ivan.instazoo.mappers.PostMapper;
import ru.ivan.instazoo.repositories.PostRepository;
import ru.ivan.instazoo.repositories.UserRepository;
import ru.ivan.instazoo.services.PostService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostMapper.INSTANCE::toPostDTO)
                .collect(Collectors.toList());
    }
    @Override
    public PostDTO postById(Long id) throws PostNotFoundException {
        Post post = getPostEntityById(id);
        return PostMapper.INSTANCE.toPostDTO(post);
    }

    @Override
    public PostDTO save(PostDTO postDTO, Principal principal) throws UserNotFoundException{
        User userByPrincipal = getUserByPrincipal(principal);
        Post postToDb = PostMapper.INSTANCE.toPost(postDTO);
        postToDb.setUser(userByPrincipal);
        return PostMapper.INSTANCE.toPostDTO(postRepository.save(postToDb));
    }

    @Override
    public void deleteById(Long id, Principal principal) throws PostNotFoundException, AccessDeniedException {
        Post post = getPostEntityById(id);
        if (post.getUser().getUsername().equals(principal.getName())){
            postRepository.delete(post);
        }
        else throw new AccessDeniedException("You haven`t access to delete Post");
    }

    private Post getPostEntityById(Long id) throws PostNotFoundException{
        return postRepository.findById(id)
                .orElseThrow( () -> new PostNotFoundException("Not found post with id:" + id));
    }

    private User getUserByPrincipal(Principal principal) throws UserNotFoundException {
        String username = principal.getName();
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
    }
}
