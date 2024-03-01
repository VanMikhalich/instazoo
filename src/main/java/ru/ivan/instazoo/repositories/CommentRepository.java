package ru.ivan.instazoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivan.instazoo.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
