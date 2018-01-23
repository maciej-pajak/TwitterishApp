package pl.maciejpajak.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.maciejpajak.entity.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    
    public Page<Tweet> findByUserId(long id, Pageable pageable); 

}
