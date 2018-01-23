package pl.maciejpajak.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.maciejpajak.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public long countByTweetId(long id);
    public Page<Comment> findByTweetId(long id, Pageable pageable);
    
}
