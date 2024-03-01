package ru.ivan.instazoo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivan.instazoo.dto.CommentDTO;
import ru.ivan.instazoo.exceptions.AccessDeniedException;
import ru.ivan.instazoo.exceptions.CommentNotFoundException;
import ru.ivan.instazoo.exceptions.PostNotFoundException;
import ru.ivan.instazoo.exceptions.UserNotFoundException;
import ru.ivan.instazoo.services.CommentService;
import ru.ivan.instazoo.validators.ResponseErrorValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CommentRestController {
    private final CommentService commentService;
    private final ResponseErrorValidator responseErrorValidator;

    @GetMapping("{postId}")
    public ResponseEntity<List<CommentDTO>> getAllByPostId(@PathVariable Long postId) throws PostNotFoundException {
        return new ResponseEntity<>(commentService.getAllByPosts(postId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CommentDTO commentDTO, BindingResult result, Principal principal) throws UserNotFoundException, PostNotFoundException {
        if (result.hasErrors()) return new ResponseEntity<>(responseErrorValidator.validate(result), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(commentService.save(commentDTO, principal), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, Principal principal) throws CommentNotFoundException, AccessDeniedException {
        commentService.deleteById(id, principal);
        return new ResponseEntity("Delete comment with id: " + id, HttpStatus.OK);
    }
}
