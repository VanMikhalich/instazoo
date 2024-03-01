package ru.ivan.instazoo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivan.instazoo.dto.PostDTO;
import ru.ivan.instazoo.exceptions.AccessDeniedException;
import ru.ivan.instazoo.exceptions.PostNotFoundException;
import ru.ivan.instazoo.exceptions.UserNotFoundException;
import ru.ivan.instazoo.payload.response.Info;
import ru.ivan.instazoo.services.PostService;
import ru.ivan.instazoo.validators.ResponseErrorValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;
    private final ResponseErrorValidator responseErrorValidator;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable("id") Long id) throws PostNotFoundException{
        return new ResponseEntity<>(postService.postById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PostDTO postDTO, BindingResult result, Principal principal) throws UserNotFoundException {
        if (result.hasErrors()) return new ResponseEntity<>(responseErrorValidator.validate(result), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(postService.save(postDTO, principal), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Info> delete(@PathVariable Long id, Principal principal) throws PostNotFoundException, AccessDeniedException {
        postService.deleteById(id, principal);
        return new ResponseEntity<>(new Info("Delete with id: " + id, true), HttpStatus.OK);
    }


}
