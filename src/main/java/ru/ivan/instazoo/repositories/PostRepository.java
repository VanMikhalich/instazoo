package ru.ivan.instazoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivan.instazoo.entities.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
